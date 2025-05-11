package az.xecore.appluni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AppluniApplication {




    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().load();

        // Передаем переменные окружения в System Properties
        System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
        System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
        System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
        System.setProperty("TOKEN_SIGNING_KEY", dotenv.get("TOKEN_SIGNING_KEY"));

        // Проверка
        System.out.println("DB URL: " + System.getProperty("SPRING_DATASOURCE_URL"));

        SpringApplication.run(AppluniApplication.class, args);
    }

}
