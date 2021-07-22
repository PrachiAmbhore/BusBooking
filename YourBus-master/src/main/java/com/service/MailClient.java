package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailClient {

	private JavaMailSender mailSender;
	private MailContentBuilder mailContentBuilder;

	@Autowired
	public MailClient(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) 
	{
		this.mailSender = mailSender;
		this.mailContentBuilder = mailContentBuilder;
	}

	public void prepareAndSend(String recipient, String message) 
	{
		MimeMessagePreparator messagePreparator = mimeMessage -> 
		{
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("devasheesh.roy@wittybrains.com");
			messageHelper.setTo("devasheesh.roy@wittybrains.com");
			messageHelper.setSubject("Sample mail subject");
			String content = mailContentBuilder.build("Mail is sent");
			messageHelper.setText(content, true);
		};
		try 
		{
			mailSender.send(messagePreparator);
		}
		catch (MailException e) {
			// runtime exception; compiler will not force you to handle it
			System.out.println(e);
		}
	}
}