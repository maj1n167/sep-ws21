import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import ude.sep.client.controller.ConnectionController;
import ude.sep.server.ServerApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest extends ConnectionController {



    @Test
    public void timeTest() throws IOException {

        //Test 1
        JSONObject input = new JSONObject();
        input.put("end", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now().plusMinutes(5)));
        int ergebnis = getDeliveryTimeTest(input.toString());
        assertEquals(4, ergebnis);


        //Test 2
        input = new JSONObject();
        input.put("end", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now().plusMinutes(20)));
        ergebnis = getDeliveryTimeTest(input.toString());
        assertEquals(19, ergebnis);
    }
}
