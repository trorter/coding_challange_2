package an.y.ledov.mock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * The Swagger configuration.
 * Available at <a href="http://{host}:{port}/swagger-ui/index.html">...</a>
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mock Service API for Fonoapi")
                        .version("v1")
                        .description("End-point for all API calls"))
                .servers(List.of(
                        new Server().url("/")));
    }
}
