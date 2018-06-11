
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({XsdGeneratorConfig.class})
public class XsdGenerator {

    public static void main(String[] args) {
        SpringApplication.run(XsdGenerator.class, args);
    }

}