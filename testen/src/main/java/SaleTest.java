import org.json.JSONArray;
import org.json.JSONObject;
import ude.sep.client.controller.ConnectionController;
import ude.sep.client.controller.LoginController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleTest extends ConnectionController {
    public static void main(String[] args) {
        JSONObject test1 = new JSONObject();
        JSONObject test2 = new JSONObject();
        test1.put("start", "13.01.2022");
        test1.put("end", "13.01.2022");
        test2.put("geburtsdatum", "11.01.2022");


        System.out.println("Testfall 1:\nAktionsdatum: 13.01.2022, Vergleich mit LocalDateTime.now(), Erwartetes Ergebnis FALSE"+"" +
                "\nAusgabe LocalDateTime.now(): "+LocalDate.now().toString()+"\nAusgabe Aktionsdatum: "+test1.getString("start"));
        System.out.println("Testfall 2:\nGeburtsdatum: 11.01.2022, Vergleich mit LocalDateTime.now(), Erwartetes Ergebnis TRUE"+"" +
                "\nAusgabe LocalDateTime.now(): "+LocalDate.now().toString()+"\nAusgabe geburtsdatum: "+test2.getString("geburtsdatum"));


        System.out.println("Teste Testfall1: ");
        boolean ergebnis1 = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate start = LocalDate.parse(test1.getString("start"), dtf);
        System.out.println("Datum Start der Aktion: " + start.toString());
        LocalDate end = LocalDate.parse(test1.getString("end"), dtf);
        System.out.println("Datum Ende der Aktion: " + end.toString());
        if (LocalDate.now().isAfter(start) && LocalDate.now().isBefore(end)) {
            ergebnis1 = true;
        }
        System.out.println("Das Ergebnis lautet: " + ergebnis1);


        System.out.println("Teste Testfall2: ");
        boolean ergebnis2 = false;
        LocalDate usergb = LocalDate.parse(test2.getString("geburtsdatum"), dtf);

        System.out.println("Geburtsdatum des Kunden: " + usergb.toString());
        if (LocalDate.now().isEqual(usergb)) {
            ergebnis2 = true;
        }
        System.out.println("Das Ergebnis lautet: " + ergebnis2);
    }

//    private Boolean hasPromotion(int restaurantId) throws IOException {
//        Boolean output = false;
//
//        JSONObject user = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyid/"+LoginController.userId).toString());
//        LocalDateTime usergb = LocalDateTime.parse(user.getString("geburtsdatum"), dtf);
//        if(LocalDateTime.now().equals(usergb)) {
//            output = true;
//        } else {
//            JSONArray ja = getPromotion(restaurantId);
//            for (int i = 0; i < ja.length(); i++) {
//                JSONObject cur = ja.getJSONObject(i);
//
//            }
//        }
//        return output;
//    }
}
