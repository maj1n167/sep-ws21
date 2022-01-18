package ude.sep.server.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.model.User;
import ude.sep.server.service.UserService;

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


    @GetMapping("/send/decline/{email}")
    public ResponseEntity<User> sendDecline(@PathVariable("email") String email) {

        User users = userService.findUserByEmail(email);
        String currentEmail = users.getEmail();
        System.out.println(currentEmail);
        userService.sendEmail(currentEmail.toString(), "Hallo "+users.getName()+",\n\n"+
                "Ihre Bestellung ist bei uns eingegangen, jedoch können wir ihren Sonderwunsch nicht umsetzen" +
                "\n \n Viele Grüße\n Ihr SuprmeOrdering Team", "Sonderwunsch");
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("/send/sonder/{id}")
    public ResponseEntity<User> sendSonder(@PathVariable("id") int id) {

        User users = userService.findUserByUserId(id);
        String currentEmail = users.getEmail();
        System.out.println(currentEmail);
        userService.sendEmail(currentEmail.toString(), "Hallo " + users.getName() + ",\n\n" +
                "Sie haben eine Sonderwunsch anfrage erhalten. Bitte bearbeiten sie die Sonderwunschanfrage so schnell wie möglich." +
                "\n \n Viele Grüße\n Ihr SuprmeOrdering Team", "Sonderwunsch");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/send/accept/{email}")
    public ResponseEntity<User> sendAccept(@PathVariable("email") String email) {

        User users = userService.findUserByEmail(email);
        String currentEmail = users.getEmail();
        System.out.println(currentEmail);
        userService.sendEmail(currentEmail.toString(), "Hallo "+users.getName()+",\n\n"+
                "Ihre Bestellung ist bei uns eingegangen,wir können ihren Sonderwunsch umsetzen und passen dazu den Preis an." +
                "\n \n Viele Grüße\n Ihr SuprmeOrdering Team", "Sonderwunsch");
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    // Send Sale Email
    @PostMapping("/send/sale/{userId}")
    public ResponseEntity<User> sendVerification(@PathVariable int userId, @RequestBody String input) throws JSONException {
        System.out.println("test bei User");
        JSONObject body = new JSONObject(input);
        User cur = userService.findUserByUserId(userId);
        String currentEmail = cur.getEmail();
        System.out.println(currentEmail);
        userService.sendEmail(currentEmail, "Hallo," +
                "\nWir haben gute Neuigkeiten für Sie!" +
                "\nIhr Lieblingsrestaurant \""+body.getString("rName")+"\" hat eine Aktion gestartet!" +
                "\nSie erhalten vom \""+body.getString("start")+"\" bis zum \""+body.getString("end")+"\" 20% Rabatt auf Ihre Bestellung!" +
                "\nMFG" +
                "\nIhr Supreme Eating Program-Team", "Eine Aktion für Sie!");
        return new ResponseEntity<>(cur, HttpStatus.OK);

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