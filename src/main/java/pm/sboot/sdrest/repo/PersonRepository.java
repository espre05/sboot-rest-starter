package pm.sboot.sdrest.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import pm.sboot.sdrest.domain.Person;

@RepositoryRestResource
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	List<Person> findByLastName(@Param("name") String name);

	@RestResource(path = "by-name")
	List<Person> findByLastNameStartsWith(@Param("name") String name, Pageable p);
}