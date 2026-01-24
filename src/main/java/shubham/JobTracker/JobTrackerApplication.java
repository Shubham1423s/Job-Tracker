package shubham.JobTracker;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  JobTrackerApplication {

	public static void main(String[] args) {


		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));

		SpringApplication.run(JobTrackerApplication.class, args);

	}

}
