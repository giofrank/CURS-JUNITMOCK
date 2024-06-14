package dev.VentaEntradas.infraestructure.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class AppConfiguration {

	@Value("${spring.application.name}")
	private String valor;
	
	@Bean 
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Bean
	public OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title(valor)
	                        .version("1.0.0")
	                        .description("Este es un aplicativo de venta de entradas")
	                        .termsOfService("http://swagger.io/terms/")
	                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));

	}
}