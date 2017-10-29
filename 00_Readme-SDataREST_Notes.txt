#Lookup later

Projections

@Projection(name = "noAddresses", types = { Person.class })
http://localhost:8080/persons/1?projection=noAddresses.

@Projection(name = "passwords", types = { User.class })
interface PasswordProjection {



@Projection(name = "virtual", types = { Person.class })
public interface VirtualProjection {

  @Value("#{target.firstName} #{target.lastName}") 
  String getFullName();
  
Excerpts

@RepositoryRestResource(excerptProjection = NoAddresses.class)
interface PersonRepository extends CrudRepository<Person, Long> {}

Excerpts - inline commmonly accessed 
@Projection(name = "inlineAddress", types = { Person.class }) 
interface InlineAddress {

@RepositoryRestResource(excerptProjection = InlineAddress.class)
interface PersonRepository extends CrudRepository<Person, Long> {}

E-Tag : Conditional operations with headers


curl -v -X PATCH -H 'If-Match: <value of previous ETag>' ...

curl -v -H 'If-None-Match: <value of previous etag>' ...

curl -H "If-Modified-Since: Wed, 24 Jun 2015 20:28:15 GMT" ...


Rest EVENTS
BeforeCreateEvent

AfterCreateEvent

BeforeSaveEvent

AfterSaveEvent

BeforeLinkSaveEvent

AfterLinkSaveEvent

BeforeDeleteEvent

AfterDeleteEvent

public class BeforeSaveEventListener extends AbstractRepositoryEventListener {

  @Override
  public void onBeforeSave(Object entity) {
    ... logic to handle inspecting the entity before the Repository saves it
  }

  @Override
  public void onAfterDelete(Object entity) {
    ... send a message that this entity has been deleted
  }
}

Integration - i.e clients?


- @Aw RepositoryEntityLinks entityLinks;



Security - Pre/Post Authorize
- must override all 4 delete methods to secure
@PreAuthorize("hasRole('ROLE_USER')") 
public interface PreAuthorizedOrderRepository extends CrudRepository<Order, UUID> {

	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@Override
	void deleteById(UUID aLong);
		
//void delete(Order order);
//void deleteAll(Iterable<? extends Order> orders);
//void deleteAll();

Method level sec
@Configuration 
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter { 
	...
}

Dev HAL Browser
- dep : spring-data-rest-hal-browser


hide
@RestResource(exported = false)
List<Person> findByName(String name);


 @OneToMany
  @RestResource(exported = false)
  private Map<String, Profile> profiles;
  
  
Adding SD Rest to existing SpringMVC app

  @Configuration
@Import(RepositoryRestMvcConfiguration.class)
public class MyApplicationConfiguration {



Repo CORS confiuraion

@CrossOrigin(origins = "http://domain2.com",
  methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE },
  maxAge = 3600)
interface PersonRepository extends CrudRepository<Person, Long> {}

CORS - global config
@Component
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

    config.getCorsRegistry().addMapping("/person/**")
      .allowedOrigins("http://domain2.com")
      .allowedMethods("PUT", "DELETE")
      .allowedHeaders("header1", "header2", "header3")
      .exposedHeaders("header1", "header2")
      .allowCredentials(false).maxAge(3600);
  }
}


