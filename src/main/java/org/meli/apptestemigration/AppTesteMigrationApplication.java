package org.meli.apptestemigration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppTesteMigrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppTesteMigrationApplication.class, args);
    }

}
