package br.com.restfullspringboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("RESTful API with Java 19 and Spring Boot 3")
                                .version("v1")
                                .description("Este repositório tem a função de servir como consulta e analise" +
                                        " para o curso o uso do Java Spring Boot, especialmente para o uso de melhores" +
                                        " praticas e padrões em APIs com Java.")
                                .termsOfService("")
                                .license(
                                        new License()
                                                .name("Licença Pública Geral GNU")
                                                .url("https://www.gnu.org/licenses/gpl-3.0.html")
                                )
                );
    }
}
