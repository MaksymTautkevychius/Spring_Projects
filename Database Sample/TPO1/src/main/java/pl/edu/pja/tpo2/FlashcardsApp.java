package pl.edu.pja.tpo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class FlashcardsApp {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(FlashcardsApp.class);
        FlashcardsController flashcardsController = context.getBean(FlashcardsController.class);
        flashcardsController.callTheMenu();
    }
}