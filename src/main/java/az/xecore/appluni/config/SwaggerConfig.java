package az.xecore.appluni.config;

import az.xecore.appluni.dto.res.ErrorResponse;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSchemas("ErrorResponse", new Schema<ErrorResponse>()
                                .type("object")
                                .properties(
                                        Map.of(
                                                "status", new IntegerSchema(),
                                                "error", new StringSchema(),
                                                "message", new StringSchema()
                                        )
                                )
                        )
                );
    }
}