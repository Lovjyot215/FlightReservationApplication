package com.lovjyot.flightReservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Value("${com.lovjyot.flightReservation.itinerary.email.body}")
	private String EMAIL_BODY;

	@Value("${com.lovjyot.flightReservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT;

	@Autowired
	private JavaMailSender sender;
	
	Logger LOGGER= LoggerFactory.getLogger(EmailUtil.class);
	
	public void sendItinerary(String toAddress, String filePath) {
		MimeMessage mimeMessage = sender.createMimeMessage();
		
		try {
			LOGGER.info("Inside  sendItinerary method..");
			MimeMessageHelper msgHelper=new MimeMessageHelper(mimeMessage, true);
			msgHelper.setTo(toAddress);
			msgHelper.setSubject(EMAIL_SUBJECT);
			msgHelper.setText(EMAIL_BODY);
			msgHelper.addAttachment("Itinerary", new File(filePath));
			LOGGER.info("ABout to email Itinerary");
			sender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
