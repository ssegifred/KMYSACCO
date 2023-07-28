package org.pahappa.systems.kimanyisacco.service.Implement;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mindrot.jbcrypt.BCrypt;
import org.pahappa.systems.kimanyisacco.DAO.UserDAO;
import org.pahappa.systems.kimanyisacco.models.Transact;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.models.adminLogin;
import org.pahappa.systems.kimanyisacco.service.Userservice;

public class UserServiceImpl implements Userservice {
    List<User> userList;

    private final UserDAO userDAO = new UserDAO();

    public void saveUser(User user) {
        String email = user.getEmail();
        String memberid = user.getMemberId();

        // Hash the password before storing it in the database
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        user.setStatus(0);

        userDAO.save(user);
        // send email to save Memberid after registration
        sendMemberEmail(email, memberid);
    }

    public List<User> getAllUsers() {

        return userDAO.getUsers();
    }

    public adminLogin getAdmin(String id) {

        return userDAO.getAdmin(id);
    }

    public User getMember(String id) {

        return userDAO.getMember(id);
    }

    public boolean deleteMember(String memberid) {
        return userDAO.deleteMember(memberid);
    }

    public boolean updateUser(User user) {
        // Hash the password before storing it in the database
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userDAO.updateUser(user);
    }

    @Override
    public boolean getAccountByUserId(String memberId, Double amount) {
        return userDAO.getAccountByUSerId(memberId, amount);
    }

    private String hashPassword(String password) {
        // Use a strong hashing algorithm
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean getWithdrawals(String memberId, Double amount) {
        return userDAO.getWithdrawals(memberId, amount);
    }

    public List<Transact> getAllTransactions(String id) {
        return userDAO.getTransactions(id);
    }

    public List<Transact> getTransactions() {
        return userDAO.getAllTransactions();
    }

    private void sendMemberEmail(String recipientEmail, String id) {
        // Configure the email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Set up the session with the authentication details
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("fredsseginda70@gmail.com", "earavhxyypuzatig");
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fredsseginda70@gmail.com", "KMYSACCO"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject("Save your membership ID");
            message.setText("Dear Member,\n\nYour memberID is " + id);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception if the email sending fails
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // Handle the exception if there's an issue with the encoding
        }
    }

}
