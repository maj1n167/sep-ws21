
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

    public static JSONArray ball = new JSONArray();
    public static JSONObject b1 = new JSONObject();
    public static JSONObject b2 = new JSONObject();
    public static JSONObject b3 = new JSONObject();

    @BeforeAll
    public static void beforeAll() {

        //Bewertung hinzufügen
        b1.put("starsFood", "5");
        b1.put("starsLieferung", "4");


        //Bewertung hinzufügen
        b2.put("starsFood", "4");
        b2.put("starsLieferung", "5");

        //Bewertung hinzufügen
        b3.put("starsFood", "2");
        b3.put("starsLieferung", "2");
    }

    @Test
    //AvgBewertung ausgeben lassen
    public void avgTest() throws IOException {
        //test 1
        ball.put(b1);
        double answerFood = avgFoodTest(ball);
        double answerLieferung = avgDeliveryTest(ball);

        assertEquals(5.0, answerFood);
        assertEquals(4.0, answerLieferung);

        System.out.println("AvgFood Test1: \nErwarteter Wert \"5.0\", berechneter Wert: " + answerFood);
        System.out.println("AvgDelivery Test1: \nErwarteter Wert \"4.0\", berechneter Wert: " + answerLieferung);

        //test 2
        ball.put(b2);
        answerFood = avgFoodTest(ball);
        answerLieferung = avgDeliveryTest(ball);

        assertEquals(4.5, answerFood);
        assertEquals(4.5, answerLieferung);

        System.out.println("AvgFood Test2: \nErwarteter Wert \"4.5\", berechneter Wert: " + answerFood);
        System.out.println("AvgDelivery Test2: \nErwarteter Wert \"4.5\", berechneter Wert: " + answerLieferung);

        //test 3
        ball.put(b3);
        answerFood = avgFoodTest(ball);
        answerLieferung = avgDeliveryTest(ball);

        assertEquals(3.7, answerFood);
        assertEquals(3.7, answerLieferung);

        System.out.println("AvgFood Test3: \nErwarteter Wert \"3.7\", berechneter Wert: " + answerFood);
        System.out.println("AvgDelivery Test3: \nErwarteter Wert \"3.7\", berechneter Wert: " + answerLieferung);
    }
}