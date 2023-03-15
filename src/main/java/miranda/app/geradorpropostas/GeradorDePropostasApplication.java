package miranda.app.geradorpropostas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeradorDePropostasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeradorDePropostasApplication.class, args);
	}

}
