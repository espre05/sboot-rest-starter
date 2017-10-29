package pm.sboot.sdrest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pm.sboot.sdrest.conf.AppInitializer;

@SpringBootApplication // equals Configuration, AutoConf, CompScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// Just to insert some data
	@Autowired
	AppInitializer initializer;

	@PostConstruct
	void init() {
		initializer.init();
	}

}