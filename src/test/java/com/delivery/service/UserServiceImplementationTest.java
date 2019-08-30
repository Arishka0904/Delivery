package com.delivery.service;

import com.delivery.domain.Role;
import com.delivery.domain.User;
import com.delivery.repository.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplementationTest {
    private UserRepo userRepo = mock(UserRepo.class);
    private MailSender mailSender = mock(MailSender.class);
    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private UserServiceImplementation userService = new UserServiceImplementation(userRepo, mailSender, passwordEncoder);
    private static final User user = new User();

    @BeforeEach
    public void setUp() {
        user.setEmail("email@email.email");
        user.setUsername("User1");
        user.setRoles(Collections.singleton(Role.USER));
    }

    @Test
    void shouldThrowUsernameNotFoundException() throws UsernameNotFoundException {


        when(userRepo.findByUsername("User2")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("User2"));
    }

    @Test
    void shouldAddNewUser() {

        userService.addNewUser(user);

        Assert.assertNotNull(user.getActivationCode());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        Mockito.verify(mailSender, Mockito.times(1))
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.eq("Activation code"),
                        ArgumentMatchers.anyString());
    }

    @Test
    void shouldReturnTrueIfUserExist() {

        when(userRepo.findByUsername("User1")).thenReturn(Optional.of(user));
        Assert.assertTrue(userService.isUserExist(user));
    }

    @Test
    void shouldReturnFalseIfUserDoesNotExist() {

        when(userRepo.findByUsername("User2")).thenReturn(null);
        Assert.assertFalse(userService.isUserExist(user));
    }

    @Test
    void shouldReturnTrueIfEmailExist() {

        when(userRepo.findByEmail("email@email.email")).thenReturn(user);
        Assert.assertTrue(userService.isEmailExist(user));
    }

    @Test
    void shouldReturnFalseIfEmailExist() {

        when(userRepo.findByEmail("email@email.email")).thenReturn(null);
        Assert.assertFalse(userService.isEmailExist(user));
    }

    @Test
    public void activateUserTest() {

        user.setActivationCode("activate");

        Mockito.doReturn(user)
                .when(userRepo)
                .findByActivationCode("activate");

        boolean isUserActivated = userService.activateUser("activate");

        Assert.assertTrue(isUserActivated);
        Assert.assertNull(user.getActivationCode());
        Mockito.verify(userRepo, Mockito.times(1)).save(user);

    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = userService.activateUser("activate");

        Assert.assertFalse(isUserActivated);
        Mockito.verify(userRepo, Mockito.never()).save(any(User.class));
    }

    @Test
    void updateUserRoleTest() {

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(Role.USER);
        user.setRoles(userRoles);
        user.setActive(true);
        user.setId(1l);
        Map<String, String> form = new LinkedHashMap<>();

        form.put("username", "User1");
        form.put("USER", "on");
        form.put("ADMIN", "on");
        form.put("userId", "1");

        userService.updateUserRole(user, "User1", form);

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void shouldUpdatePassword() {
        user.setPassword("123");
        userService.updateProfile(user, "321", "email@email.email");
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        Mockito.verify(mailSender, Mockito.never())
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.eq("Activation code"),
                        ArgumentMatchers.anyString());
    }

    @Test
    void shouldUpdateEmail() {
        user.setPassword("123");
        userService.updateProfile(user, "123", "else_email@email.email");
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        Mockito.verify(mailSender, Mockito.times(1))
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.eq("Activation code"),
                        ArgumentMatchers.anyString());
    }
}
