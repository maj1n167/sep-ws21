package ude.sep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ude.sep.model.User;
import ude.sep.service.UserService;

import java.util.List;
import java.util.Random;

@Component
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.findAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    // Sending Verifycode to Email
    @GetMapping("/send/verification/{email}")
    public ResponseEntity<User> sendVerification(@PathVariable("email") String email) {

        User users = userService.findUserByEmail(email);
        String currentEmail = users.getEmail();
        System.out.println(currentEmail);
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        users.setVerifyCode(number);
        userService.updateUser(users);
        int code = users.getVerifyCode();
        userService.sendEmail(currentEmail.toString(), "Ihr Verifizierung Code: "+code, "Verifizierungcode");
        return new ResponseEntity<>(users, HttpStatus.OK);

    }
    @GetMapping("/send/orderVerification/{email}")
    public ResponseEntity<User> sendOrderVerification(@PathVariable("email") String email) {

        User users = userService.findUserByEmail(email);
        String currentEmail = users.getEmail();
        System.out.println(currentEmail);
        userService.sendEmail(currentEmail.toString(), "Hallo "+users.getName()+",\n\n"+
                "Ihre Bestellung ist bei uns eingegangen.\n \n Viele Grüße\n Ihr SuprmeOrdering Team", "Bestätigungsmail");
        return new ResponseEntity<>(users, HttpStatus.OK);

    }
    // Zum Abgleich, ob eine Registrierung bereits vorhanden ist und um die Daten der registrierten Person zu erhalten.
    @GetMapping("/findbyemail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User users = userService.findUserByEmail(email);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("id") int id) {
        User users = userService.findUserByUserId(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    // Bei abgeschlossener Registrierung wird so die Information auf der Datenbank hinterlegt und bestätigt
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User users) {
        User newUser = userService.addUser(users);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Bei Änderungen durch den Nutzer seiner Informationen werden hierdurch die Änderungen in der Datenbank hinterlegt und bestätigt
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User users) {
        User updateUser = userService.addUser(users);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}