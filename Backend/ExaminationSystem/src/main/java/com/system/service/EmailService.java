package com.system.service;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired LoggingService logger;
	private final String className = this.getClass().getName().toString();
	public void sendEmail(String to, String from, String subject, String text) throws SQLException, IOException {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setFrom(from);
		msg.setSubject(subject);
		msg.setText(text);
		System.out.println(msg);
		try {
			javaMailSender.send(msg);
			logger.log("info", "Email successfully sent", this.className);
		}
		catch(Exception e) {
			logger.log("error", e.getStackTrace().toString(), this.className);
		}
		
	}
	
}
