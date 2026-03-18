package com.placementportal.util;

import org.springframework.stereotype.Service;

/**
 * EmailService — placeholder for future email notifications.
 *
 * To enable real email sending, add spring-boot-starter-mail to pom.xml,
 * configure spring.mail.* in application.properties, and implement the
 * methods below using JavaMailSender.
 */
@Service
public class EmailService {

    /**
     * Send a notification email to the student when their application status changes.
     *
     * @param toEmail   recipient email address
     * @param subject   email subject
     * @param body      email body text
     */
    public void sendEmail(String toEmail, String subject, String body) {
        // TODO: Inject JavaMailSender and implement actual email sending
        System.out.println("=== EMAIL NOTIFICATION ===");
        System.out.println("To      : " + toEmail);
        System.out.println("Subject : " + subject);
        System.out.println("Body    : " + body);
        System.out.println("==========================");
    }

    /**
     * Notify a student that they have been shortlisted.
     */
    public void sendShortlistNotification(String toEmail, String studentName, String companyName) {
        String subject = "Congratulations! You've been shortlisted by " + companyName;
        String body = "Dear " + studentName + ",\n\n"
                + "We are pleased to inform you that you have been SHORTLISTED for "
                + companyName + ".\n\nBest of luck!\n\nPlacement Cell";
        sendEmail(toEmail, subject, body);
    }

    /**
     * Notify a student about their interview schedule.
     */
    public void sendInterviewNotification(String toEmail, String studentName,
                                          String companyName, String date, String time) {
        String subject = "Interview Scheduled – " + companyName;
        String body = "Dear " + studentName + ",\n\n"
                + "Your interview with " + companyName + " has been scheduled.\n"
                + "Date : " + date + "\n"
                + "Time : " + time + "\n\n"
                + "Please be prepared.\n\nPlacement Cell";
        sendEmail(toEmail, subject, body);
    }

    /**
     * Notify a student that they have been selected.
     */
    public void sendSelectionNotification(String toEmail, String studentName, String companyName) {
        String subject = "Selected! Offer from " + companyName;
        String body = "Dear " + studentName + ",\n\n"
                + "Congratulations! You have been SELECTED by " + companyName + ".\n\n"
                + "The placement team will share further details soon.\n\nPlacement Cell";
        sendEmail(toEmail, subject, body);
    }
}
