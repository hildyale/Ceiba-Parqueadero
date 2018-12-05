package co.com.ceiba.ceibaParqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CeibaParqueaderoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeibaParqueaderoApplication.class, args);
	}
}
