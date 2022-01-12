
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import ude.sep.server.ServerApplication;
import ude.sep.client.controller.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AvgBewertungTest  extends ConnectionController {

    public int restId = 1;

    @BeforeAll
    public static void beforeAll() {


        JFXPanel fxPanel = new JFXPanel();
        ServerApplication s = new ServerApplication();

        ConnectionController myConController = new ConnectionController();
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
                    System.out.println("UserId im Login: " + login.getUserId());
                } catch (IOException e) {
                    e.printStackTrace();
                }


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
                    System.out.println(addRestaurant.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

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



                //Bewertung hinzufügen
                bewertung.gerichtChoiceBox.setValue("5");
                bewertung.lieferungChoiceBox.setValue("4");
                bewertung.nameTextfield.setText("hans");
                bewertung.kommentarTextfield.setText("Alles top");
                try {
                    bewertung.speichernButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Bewertung hinzufügen
                bewertung.gerichtChoiceBox.setValue("4");
                bewertung.lieferungChoiceBox.setValue("5");
                bewertung.nameTextfield.setText("wurst");
                bewertung.kommentarTextfield.setText("Gut, gut");
                try {
                    bewertung.speichernButtonClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }





        });
    }


    @Test
    public void restaurantTest() throws IOException{
        JSONObject answer = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+restId).toString());
        String expected =
        "{ \"name\": \"" + "KrosseKrabbe" + "\",\n" +
                " \"restaurantId\": \"" + restId + "\",\n" +
                " \"strasse\": \"" + "Schuetzenbahn" + "\",\n" +
                " \"nummer\": \"" + "70" + "\",\n" +
                " \"plz\": \"" + "45127" + "\",\n" +
                " \"stadt\": \"" + "Essen" + "\",\n" +
                " \"mbw\": \"" + "5" + "\",\n" +
                " \"lieferkosten\": \"" + "1" + "\",\n" +
                " \"kategorie\": \"" + "Italienisch"+ "\",\n" +
                " \"ratingFood\": \"" + 5.0 + "\",\n" +
                " \"ratingDelivery\": \"" + 4.0 + "\",\n" +
                " \"lieferbereich\": \"" + "7000" + "\"\n}";
        assertEquals(expected, answer);

    }

    @Test
    //AvgBewertung ausgeben lassen
    public void avgTest() throws IOException {
        LoginController login;
        JSONObject answer = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+restId).toString());
       // JSONObject answer = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/").toString());
        double expected = 4.5;
        assertEquals(answer.getDouble("ratingFood"), expected);
        assertEquals(answer.getDouble("ratingDelivery"), expected);
        System.out.println(expected);
        System.out.println(answer);
    }


}
















