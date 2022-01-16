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

    int input = 0;

    @Test
    public void timeTest() throws IOException {

        //Test 1
        input = LocalDateTime.now().plusMinutes(5).getMinute();
        int ergebnis = getDeliveryTimeTest(input);
        assertEquals(5, ergebnis);
    }
}
