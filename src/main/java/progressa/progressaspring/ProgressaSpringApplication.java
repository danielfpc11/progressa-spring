package progressa.progressaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/spring-config.xml")
public class ProgressaSpringApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ProgressaSpringApplication.class, args);
	}

}
