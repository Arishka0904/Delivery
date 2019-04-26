package com.delivery.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MailConfig {

    private MailProperties mailProperties;

    public MailConfig(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", mailProperties.getProtocol());
        properties.setProperty("mail.debug", mailProperties.getDebug());
        properties.setProperty("mail.smtp.auth", mailProperties.getSmtp().getAuth());
        properties.setProperty("mail.smtp.starttls.enable", mailProperties.getSmtp().getEnable());

        return mailSender;
    }
}

