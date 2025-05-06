package com.opom.jobfinder.utility.services;

import com.opom.jobfinder.model.entity.job.JobApplication;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class EmailService {
    @Value("${app.mail.name}")
    private String fromMail;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, String name, JobApplication.Status status,
                          String companyName, String jobTitle, LocalDateTime appliedAt) {
        String text = jobApplicationEmailFormat(name, status, companyName, jobTitle, appliedAt);
        String subject = jobTitle.concat(" at ").concat(companyName);
        sendEmail(email, subject,text);
    }

    private void sendEmail(String email, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text, true);

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Email failed to send : {}", e.getMessage());
        }
    }

    private String jobApplicationEmailFormat(String name, JobApplication.Status status, String companyName, String jobTitle, LocalDateTime appliedAt) {
        String message = switch (status) {
            case Pending ->
                    "Your application has been received and is currently <strong>Pending</strong>. The employer will review it soon.";
            case Seen ->
                    "Your application has been <strong>Seen</strong> by the employer. They are reviewing your profile.";
            case Accepted ->
                    "Congratulations! Your application has been <strong>Approved</strong>. The employer will contact you regarding the next steps.";
            case Rejected ->
                    "Unfortunately, your application has been <strong>Rejected</strong>. We encourage you to explore other opportunities.";
        };

        return "<h2>Dear " + name + ",</h2>"
                + "<p>Your application for the position of <strong>" + jobTitle + "</strong> at <strong>" + companyName + "</strong> has been processed.</p>"
                + "<p>Status: " + message + "</p>"
                + "<p>Applied At: " + appliedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "</p>"
                + "<br>"
                + "<p>Best regards,</p>"
                + "<p>" + fromMail + "</p>";
    }
}