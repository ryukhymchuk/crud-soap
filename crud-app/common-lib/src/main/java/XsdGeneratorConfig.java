import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@Configuration
public class XsdGeneratorConfig {
    @Bean
    public XsdSchema clientSchema() {
        return new SimpleXsdSchema(new ClassPathResource("clients.xsd"));
    }
}