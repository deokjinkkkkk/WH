package co.admin.wh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WhProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhProjectApplication.class, args);
	}

}
  