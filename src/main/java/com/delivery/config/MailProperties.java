package com.delivery.config;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@Component
@PropertySource("classpath:mail.properties")
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    @ToString
    @Getter
    @Setter
    public static class Smtp {

        private String auth;
        private String enable;

    }
    @NotBlank
    private String host;
    private String username;
    private String password;
    private int port;
    private String protocol;
    private Smtp smtp;
    private String debug;

}
