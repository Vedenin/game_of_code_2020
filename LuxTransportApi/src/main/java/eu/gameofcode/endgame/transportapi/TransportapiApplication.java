package eu.gameofcode.endgame.transportapi;

import eu.gameofcode.endgame.transportapi.data.DataInitializator;
import eu.gameofcode.endgame.transportapi.model.Route;
import eu.gameofcode.endgame.transportapi.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

@SpringBootApplication
public class TransportapiApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(TransportapiApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		DataInitializator dataInitializator = applicationContext.getBean(DataInitializator.class);
		dataInitializator.initializeData();

	}
}
