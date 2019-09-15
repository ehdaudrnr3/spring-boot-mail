package com.test.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.mail.MailNotifier;

@RestController
public class MailController {
	
	@Autowired
	MailNotifier mailNotifier;
	
	@RequestMapping("/mail/{hostName}")
	public String send(
			@PathVariable("hostName") String hostName) {
		mailNotifier.sendMail(hostName);
		return hostName.toUpperCase()+" 메일 전송이 완료되었습니다.";
	}
}
