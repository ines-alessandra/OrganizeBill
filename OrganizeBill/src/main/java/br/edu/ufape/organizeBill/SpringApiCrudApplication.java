package br.edu.ufape.organizeBill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringApiCrudApplication {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ConfigurableApplicationContext appCtx = SpringApplication.run(SpringApiCrudApplication.class, args);
	}

}
