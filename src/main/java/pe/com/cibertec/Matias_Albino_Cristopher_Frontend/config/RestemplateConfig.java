package pe.com.cibertec.Matias_Albino_Cristopher_Frontend.config;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){

        return  builder
                .setReadTimeout(Duration.ofSeconds(30))
                .build();
        //return new RestTemplate();
    }
}
