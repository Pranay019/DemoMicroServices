package com.demo.Notifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Notifications.services.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {
	public EmailController() {
		System.out.println("emailcontroller obj created");
	}

	@Autowired
	private EmailService emailService;

	@PostMapping("/send")
	public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
		emailService.sendSimpleEmail(to, subject, text);
		return "Email sent successfully!";
	}

	public String sendHtmlEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String html)
			throws MessagingException {
		emailService.sendHtmlEmail(to, subject, html);
		return "HTML Email sent successfully!";
	}
}
