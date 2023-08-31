package ma.ens.AviCultureBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class AviCultureBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AviCultureBackendApplication.class, args);
	}

}
