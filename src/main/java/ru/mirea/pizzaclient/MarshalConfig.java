package ru.mirea.pizzaclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class MarshalConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("ru.mirea.pizzaclient.model.xml");
        return marshaller;
    }

    @Bean
    public PizzaClient countryClient(Jaxb2Marshaller marshaller) {
        PizzaClient client = new PizzaClient();
        client.setDefaultUri("http://localhost:8080/ws/pizza");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
