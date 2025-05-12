package az.xecore.appluni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class AppluniApplication {




    public static void main(String[] args) {

        // Load .env before Spring starts
        if (Files.exists(Paths.get(".env"))) {
            Dotenv dotenv = Dotenv.configure().load();

            // Pass env-s in System Properties
            System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
            System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
            System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
            System.setProperty("TOKEN_SIGNING_KEY", dotenv.get("TOKEN_SIGNING_KEY"));

          // Test logs (Property)
            System.out.println("[PROPERTY] DB URL: " + System.getProperty("SPRING_DATASOURCE_URL"));
            System.out.println("[PROPERTY] DB USERNAME: " + System.getProperty("SPRING_DATASOURCE_USERNAME"));
            System.out.println("[PROPERTY] DB PASSWORD: " + System.getProperty("SPRING_DATASOURCE_PASSWORD").length() + " characters");
        }
        else {
            // Test logs (Env)
            System.out.println("[ENV] DB URL: " + System.getenv("SPRING_DATASOURCE_URL"));
            System.out.println("[ENV] DB USERNAME: " + System.getenv("SPRING_DATASOURCE_USERNAME"));
            System.out.println("[ENV] DB PASSWORD: " + System.getenv("SPRING_DATASOURCE_PASSWORD"));
        }
        SpringApplication.run(AppluniApplication.class, args);
    }

}
