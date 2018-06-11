package apiws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class RequestConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("apiws");
        return marshaller;
    }

    @Bean
    public ClientRequest quoteClient(Jaxb2Marshaller marshaller) {
        ClientRequest client = new ClientRequest();
        client.setDefaultUri("http://localhost:8889/ws/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}