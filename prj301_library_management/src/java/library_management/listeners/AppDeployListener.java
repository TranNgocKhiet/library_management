/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

// import library_management.dao.UserDAO;
// import library_management.dto.UserDTO;

/**
 *
 * @author Slayer
 */
@WebListener
public class AppDeployListener implements ServletContextListener {

    private void sendEmail(String toEmail, String subject, String content) {
        final String username = "tranngockhiet22062005@gmail.com";
        final String password = "cabi jkqy ujmk lqdd"; // ❗ Không nên hardcode thông tin này trong thực tế

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toEmail)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Email sent to: " + toEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // SEND TO ALL EMAIL IN DATABASE WITH STATUS ACTIVE
    /*
    private void notifyAllUsers(String subject, String message) {
        try {
            UserDAO userDAO = new UserDAO();
            List<UserDTO> users = userDAO.getAllUser();

            for (UserDTO user : users) {
                if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                    sendEmail(user.getEmail(), subject, message);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String subject = "Application Deployment Notice";
        String content = "The Library Management System has been successfully deployed on " + timestamp + ".\n\nThis is an automated message.";

        sendEmail("slayersilver2005@gmail.com", subject, content);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String subject = "Application Shutdown Notice";
        String content = "The Library Management System has been undeployed on " + timestamp + " (e.g., for maintenance or updates).\n\nThis is an automated message.";

        sendEmail("slayersilver2005@gmail.com", subject, content);
    }
}
