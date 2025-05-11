package az.xecore.appluni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AppluniApplication {




    public static void main(String[] args) {


//        Get vars from .env TODO: check .env if file exists
//        Dotenv dotenv = Dotenv.configure().load();
//
//        // Pass env-s in System Properties
//        System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
//        System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
//        System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
//        System.setProperty("TOKEN_SIGNING_KEY", dotenv.get("TOKEN_SIGNING_KEY"));
//
//        // Test logs
        System.out.println("DB URL: " + System.getenv("SPRING_DATASOURCE_URL"));
        System.out.println("DB USERNAME: " + System.getenv("SPRING_DATASOURCE_USERNAME"));
        System.out.println("DB PASSWORD: " + System.getenv("SPRING_DATASOURCE_PASSWORD"));



        SpringApplication.run(AppluniApplication.class, args);
    }

}
