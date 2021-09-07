package com.onegateafrica.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.onegateafrica.email.EmailSender;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService implements EmailSender {

  private final JavaMailSender mailSender;

  @Override
  @Async
  public void send(String to, String email) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
      helper.setText(email);
      helper.setTo(to);
      helper.setSubject("INVITATION");
      helper.setFrom("test@localhost.com");
      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      log.error("Failed to send email", e);
      throw new IllegalStateException("Failed to send email");
    }
  }
}
