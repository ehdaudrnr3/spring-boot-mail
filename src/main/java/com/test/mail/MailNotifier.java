package com.test.mail;

import java.util.Properties;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.test.mail.enums.Host;
import com.test.mail.enums.MailUser;

@Component
public class MailNotifier {
	
	@Resource(name = "googleSender")
	private JavaMailSender googleSender;
	
	@Resource(name = "naverSender")
	private JavaMailSender naverSender;
	
	@Resource(name = "daumSender")
	private JavaMailSender daumSender;
	
	public void sendMail(String hostName) {
		try {
			Host host = Host.findByHost(hostName);
			if(host == Host.GOOGLE) {
				MimeMessage mailMessage = googleSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
				helper.setSubject("구글 메일전송");
				helper.setText("구글 메일전송입니다.");
				helper.setTo(MailUser.NAVER_MAIL);
				
				googleSender.send(mailMessage);
			} else if(host == Host.NAVER){
				MimeMessage mailMessage = naverSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
				helper.setFrom(MailUser.NAVER_MAIL);
				helper.setSubject("네이버 메일전송");
				helper.setText("네이버 메일전송입니다.");
				helper.setTo(MailUser.DAUM_MAIL);
				
				naverSender.send(mailMessage);
			} else if(host == Host.DAUM) {
				MimeMessage mailMessage = daumSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
				helper.setFrom(MailUser.DAUM_MAIL);
				helper.setSubject("다음 메일전송");
				helper.setText("다음 메일전송입니다.");
				helper.setTo(MailUser.NAVER_MAIL);
				
				daumSender.send(mailMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail(String hostName, String fileName) {
		MimeMessage message = googleSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom("ehdaudrnr3@gmail.com");
			helper.setTo("ehdaudrnr3@daum.net");
			helper.setSubject("테스트제목");
			helper.setText("<span>안녕하세요</span>");

			DataSource dataSource = new FileDataSource(fileName);
			helper.addAttachment(MimeUtility.encodeText(fileName, "utf-8", "B"), dataSource);
			googleSender.send(message);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
