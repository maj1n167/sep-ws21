package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ude.sep.server.model.User;
import ude.sep.server.repo.UserRepo;

import java.util.List;


@Service
public class UserService {
    private final UserRepo userRepo;

        @Autowired
        public UserService(UserRepo userRepo) {
            this.userRepo = userRepo;
        }

        public User addUser(User users){
            return userRepo.save(users);
        }

        public List<User> findAllUsers(){
            return userRepo.findAll();
        }

        public User updateUser(User users){
            return userRepo.save(users);
        }

        public void deleteUser(int userId){
        userRepo.deleteById(userId);
    }

        @Autowired
        private JavaMailSender mailSender;

    //Method to send Emails
        public void sendEmail(String toEmail,
                              String body,
                              String subject) {

            SimpleMailMessage message = new SimpleMailMessage();


            message.setFrom("suprmeorderingsystem@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);

            mailSender.send(message);
            System.out.println("Mail Send...");

        }

        public User findUserByEmail(String email) {
            return userRepo.findUserByEmail(email);
        }

        public User findUserByUserId(int id) { return userRepo.findUserByUserId(id); }
}
