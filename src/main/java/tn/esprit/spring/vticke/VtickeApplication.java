package tn.esprit.spring.vticke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VtickeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VtickeApplication.class, args);
    }

}
