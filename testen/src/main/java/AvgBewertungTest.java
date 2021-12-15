
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import ude.sep.client.controller.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class AvgBewertungTest  extends ConnectionController {

    @Test
    public void testAvgBewertung() throws IOException {

        JFXPanel fxPanel = new JFXPanel();
        RegistrationController registration = new RegistrationController();
        LoginController login = new LoginController();
        StartseiteController startseite = new StartseiteController();
        AddRestaurantController addRestaurant = new AddRestaurantController();
        AddFoodController addFood = new AddFoodController();

        KundeRegistrationController kRegister = new KundeRegistrationController();
        RestaurantsController restaurants = new RestaurantsController();
        BewertungController bewertung = new BewertungController();


        //Erstellung Geschaeftskunde & Erstellung Restaurant
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //Registrierung Restaurantbesitzer

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
                //Login Restaurantbesitzer
                login.inputEmail.setText("ok@ok");
                login.inputPassword.setText("ok");
                try {
                    login.onLoginButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("UserId im Login: " + login.getUserId());

                //Restaurant muss hinzugefügt werden
                addRestaurant.nameTextfield.setText("KrosseKrabbe");
                addRestaurant.strasseTextfield.setText("Schuetzenbahn");
                addRestaurant.nummerTextfield.setText("70");
                addRestaurant.plzTextfield.setText("45127");
                addRestaurant.stadtTextfield.setText("Essen");
                addRestaurant.mbwTextfield.setText("5");
                addRestaurant.lieferkostenTextfield.setText("1");
                addRestaurant.kategorieChoicebox.setValue("Italienisch");
                addRestaurant.lieferbereichTextfield.setText("7000");
                try {
                    addRestaurant.speichernButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /**
                 *  String json = "{ \"name\": \"" + nameTextfield.getText() + "\",\n" +
                 *                             " \"restaurantId\": \"" + userId + "\",\n" +
                 *                             " \"strasse\": \"" + strasseTextfield.getText() + "\",\n" +
                 *                             " \"nummer\": \"" + nummerTextfield.getText() + "\",\n" +
                 *                             " \"plz\": \"" + plzTextfield.getText() + "\",\n" +
                 *                             " \"stadt\": \"" + stadtTextfield.getText() + "\",\n" +
                 *                             " \"mbw\": \"" + mbw + "\",\n" +
                 *                             " \"lieferkosten\": \"" + lieferkosten + "\",\n" +
                 *                             " \"kategorie\": \"" + kategorieChoicebox.getValue() + "\",\n" +
                 *                             " \"ratingFood\": \"" + 0.0 + "\",\n" +
                 *                             " \"ratingDelivery\": \"" + 0.0 + "\",\n" +
                 *                             " \"lieferbereich\": \"" + radius + "\"\n}";
                 */
                // Ein Gericht muss hinzugefügt werden
                addFood.name.setText("Pizza Salami");
                addFood.beschreibung.setText("Pizza mit Salami");
                addFood.preis.setText("5");
                addFood.kategorie.setText("Pizza");
                try {
                    addFood.speichernClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Registrierung Kunde
                registration.kunde.setSelected(true);
                registration.emailTextfield.setText("a@a");
                registration.vornameTextfield.setText("a");
                registration.nameTextfield.setText("a");
                registration.passwortTextfield.setText("a");
                try {
                    registration.onRegisterButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                kRegister.geburtsdatumTextfield.setText("20.11.1996");
                kRegister.strasseTextfield.setText("Lindenallee");
                kRegister.nummerTextfield.setText("91");
                kRegister.postleitzahlTextfield.setText("45127");
                try {
                    kRegister.onKundeRegisterButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Kunden Login
                login.inputEmail.setText("a@a");
                login.inputPassword.setText("a");
                try {
                    login.onLoginButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("UserId im Login: " + login.getUserId());


                //Restaurant auswählen


                //Gericht bestellen


                //Bewertung hinzufügen
                bewertung.gerichtChoiceBox.setValue("5");
                bewertung.lieferungChoiceBox.setValue("4");
                bewertung.nameTextfield.setText("hans");
                bewertung.kommentarTextfield.setText("wurst");
                try {
                    bewertung.speichernButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //AvgBewertung ausgeben lassen


                // Am Ende

                JSONObject answer = bewertung.getAnswer();
                JSONObject expected = new JSONObject("{\"response_data\":{\"body\":{\"data\":\"succeed\"}},\"response_type\":\"NEUEBEWERTUNG_ANSWER\"}");
                String answerS = answer.toString();
                String expectedS = expected.toString();
                assertEquals(expectedS, answerS);

            }
        });
    }
}
















