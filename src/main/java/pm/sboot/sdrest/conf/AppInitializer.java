package pm.sboot.sdrest.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pm.sboot.sdrest.domain.Person;
import pm.sboot.sdrest.repo.PersonRepository;

@Configuration
public class AppInitializer{
	
	public void init() {
		securlyInsertInitData();
	}
	private void securlyInsertInitData() {
		//SecurityUtils.runAs("system", "system", "ROLE_ADMIN");
		addSomeData();
		//SecurityContextHolder.clearContext();
	}

	@Autowired
	PersonRepository personRepo;
	

	private void addSomeData() {
		personRepo.save(new Person("Jane", "Doe"));
		personRepo.save(new Person("Peter", "Smith"));
	}


}