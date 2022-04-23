package com.wolvesflix.helper;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public SendMail() {

	}

	public SendMail(String mailFrom, String mailTo, String subject, String content) {
		super();
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.subject = subject;
		this.content = content;
	}

	private String mailTo;
	private String mailFrom;
	private String subject;
	private String content;

	public void sendEmail() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				String username = "briansudosu@gmail.com";
				String password = "redazxqgjeeoldau";
				return new PasswordAuthentication(username, password);
			}
		});

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(mailFrom));
			message.setRecipients(Message.RecipientType.TO, mailTo);
			message.setSubject(subject, "utf-8");
			message.setText(content, "utf-8", "html");
			message.setReplyTo(message.getFrom());
			Transport.send(message);
			System.err.println("Sent");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param mailTo the mailTo to set
	 */
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	/**
	 * @param mailFrom the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
