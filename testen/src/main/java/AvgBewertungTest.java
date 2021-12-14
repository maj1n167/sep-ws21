
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import ude.sep.client.controller.BewertungController;
import ude.sep.client.controller.ConnectionController;
import java.io.IOException;


public class AvgBewertungTest  extends ConnectionController {
    BewertungController bewertung = new BewertungController();
    String gk = "{\n  \"name\": \"ok\",\n  \"vorname\": \"ok\"\n  \"email\": \"ok@ok\",\n  \"password\": \"ok\",\n  \"restaurantBesitzer\": true,\n}";
//    String pk = "{\n  \"name\": \"ok\",\n  \"vorname\": \"ok\"\n  \"email\": \"ok@ok\",\n  \"password\": \"ok\",\n  \"strasse\": \"Hermannstr.\",\n  \"nummer\": \"9\",\n  \"plz\": \"45327\",\n  \"stadt\": \"Essen\",\n  \"restaurantBesitzer\": false,\n  \"treuepunkte\": 0,\n  \"guthaben\": 0.0,\n  \"altAdresse\": \"an der Huetung\",\n  \"altNummer\": \"1\",\n  \"altPlz\": \"Essen\",\n  \"altStadt\": \"45327\",\n}";



    @Test
    public void testAvgBewertung() throws IOException {
        //Erstellung Geschaeftskunde
        JSONObjectPOST("http://localhost:8080/user/add", gk);

        //Erstellung Restaurant
        JSONObject gkRest = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyemail/ok@ok"));
        gkRest.put("restaurantId", "1");
        gkRest.put("strasse", "wasauchimmer");
        gkRest.put("nummer", "");
        gkRest.put("plz", "");
        gkRest.put("stadt", "");
        gkRest.put("mbw", "8.0");
        gkRest.put("lieferkosten", "3.0");
        gkRest.put("ratingFood", "0.0");
        gkRest.put("ratingDelivery", "0.0");
        gkRest.put("lieferbereich", "3000");
        JSONObjectPOST("http://localhost:8080/user/add", gkRest.toString());


        // Erstellung der Bewertungen

        bewertung.gerichtChoiceBox.setValue("5");
        bewertung.lieferungChoiceBox.setValue("4");
        bewertung.nameTextfield.setText("hans");
        bewertung.kommentarTextfield.setText("wurst");
        bewertung.speichernButtonClick();
    }

}
