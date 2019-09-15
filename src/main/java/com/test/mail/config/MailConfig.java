package com.test.mail.config;

import java.util.Properties;

import javax.mail.Authenticator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.test.mail.enums.MailUser;

@Configuration
public class MailConfig {

	@Value("${google.host}")
	private String googleHost;
	
	@Value("${naver.host}")
	private String naverHost;
	
	@Value("${daum.host}")
	private String daumHost;
	
	@Bean(name = "googleSender")
	public JavaMailSender googleSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(googleHost);
		sender.setUsername(MailUser.GOOGLE_MAIL);
		sender.setPassword(MailUser.GOOGLE_PASSWORD);
		sender.setPort(587);
		sender.setDefaultEncoding("utf-8");
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.debug", "true");
		sender.setJavaMailProperties(properties);
		
		return sender;
	}
	
	@Bean(name = "naverSender")
	public JavaMailSender naverSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(naverHost);
		sender.setUsername(MailUser.NAVER_MAIL);
		sender.setPassword(MailUser.NAVER_PASSWORD);
		sender.setPort(465);
		sender.setDefaultEncoding("utf-8");
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", naverHost);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.ssl.enable", "true");
		properties.setProperty("mail.debug", "true");
		
		sender.setJavaMailProperties(properties);
		
		return sender;
	}
	
	@Bean(name = "daumSender")
	public JavaMailSender daumSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(daumHost);
		sender.setUsername(MailUser.DAUM_MAIL);
		sender.setPassword(MailUser.DAUM_PASSWORD);
		sender.setPort(465);
		sender.setDefaultEncoding("utf-8");
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", daumHost);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.ssl.enable", "true");
		properties.setProperty("mail.debug", "true");
		sender.setJavaMailProperties(properties);
		
		return sender;
	}
}
