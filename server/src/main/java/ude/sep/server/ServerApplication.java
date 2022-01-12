package ude.sep.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ServerApplication {


    public static int toAdd = 0;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }


    public static LocalDateTime getTime() {
        LocalDateTime output = LocalDateTime.now().plusMinutes(toAdd);
        return output;
    }
}
