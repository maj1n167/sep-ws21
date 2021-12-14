
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import ude.sep.client.controller.*;

import javax.servlet.Registration;
import java.io.IOException;


public class AvgBewertungTest  extends ConnectionController {

    @Test
    public void testAvgBewertung() throws IOException {

        JFXPanel fxPanel = new JFXPanel();
        RegistrationController registration = new RegistrationController();
        LoginController login = new LoginController();
        StartseiteController startseite = new StartseiteController();


        //Erstellung Geschaeftskunde & Erstellung Restaurant
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                registration.businessUser.setSelected(true);
                registration.emailTextfield.setText("ok@ok");
                registration.vornameTextfield.setText("ok");
                registration.nameTextfield.setText("ok");
                registration.passwortTextfield.setText("ok");
                try {
                    registration.onRegisterButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                login.inputEmail.setText("ok@ok");
                login.inputPassword.setText("ok");
                try {
                    login.onLoginButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        System.out.println("UserId im Login: "+login.getUserId());
        /*
        Platform.runLater(new Runnable(){
            @Override
            public void run (){
                login.inputEmail.setText("ok@ok");
                login.inputPassword.setText("ok");

                try {
                    login.onLoginButtonClick();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        */
        //Login Geschaeftskunde



        //Erstellung Restaurant


//---------------------------------------------------------------------------------
        // Erstellung der Bewertungen

//        bewertung.gerichtChoiceBox.setValue("5");
//        bewertung.lieferungChoiceBox.setValue("4");
//        bewertung.nameTextfield.setText("hans");
//        bewertung.kommentarTextfield.setText("wurst");
//        bewertung.speichernButtonClick();
    }

}
