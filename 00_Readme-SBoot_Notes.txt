


# Do not use starter-parent! In real life you may need to have corporate parent.
# Use dependencyManagement> dependency> spring-boot-dependencies> version>1.5.8.RELEASE


# To quicken the 
# App in the root of all other classes. with @EnableAutoConfiguration
# Also can use @SpringBootApplication
# @SpringBootApplication = @Configuration @EnableAutoConfiguration @ComponentScan

Layering Config.
 - using @ImportResource, for separating Config and layering
 
Autoconfig
- backs away when you define database.
- can specifically be excluded: @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})



# SB DevTools 
- only applicable during dev.
- Disables template caching - which is useful only in production.
- auto restarts when files in classpath change
- DT requires shutdownhook, so if shutdownhook disabled won't work.
- Base-classloader : all 3rd party classes.
- Restart-classloader: your app classes that change. [RCLoader is discarded]
- watch additional paths :spring.devtools.restart.additional-paths
- Exclude paths for restart: spring.devtools.restart.exclude
- if it does not work with specific lib: System.setProperty("spring.devtools.restart.enabled", "false"); SpringApplication.run(....)
- workaround for IDE hickups: spring.devtools.restart.trigger-file
- 	

# Rempote appplications
- optin <excludeDevtools>false</excludeDevtools>
- spring.devtools.remote.secret=mysecret - in properties

#### Banner 
- add banner.txt 

Listening to applicaation events
-META-INF/spring.factories : org.springframework.context.ApplicationListener=com.example.project.MyListener
- ApplicationStartingEvent, ApplicationEnvironmentPreparedEvent, ApplicationPreparedEvent
- ApplicationReadyEvent, ApplicationFailedEvent
- 

# Admin MBeans enable
spring.application.admin.enabled

# 

Profiles
- @Configuration can be sliced with @Profile("prod")
- Available profiles in application.properties: spring.profiles.active=dev,hsqldb
- Profile in cmd: --spring.profiles.active=dev,hsqldb


Logging
- details in application.properties
- for logback : logback-spring.xml  [avoid using logback.xml, as spring can't controll much]
- for log4j2 : log4j2-spring.xml


Spring WEB-MVC

- @RestController
- @RequestMapping(value="/users")
- @RequestMapping(value="/{user}", method=RequestMethod.GET)
- @JsonComponent - for json de/serializer


Error mssage codes
- spring.mvc.message-codes-resolver.format

Static Content
- serves static content from /static or /public
- spring.mvc.static-path-pattern=/resources/**
- spring.resources.static-locations : put index.html in that location.
- Webjars can be served from /webjars/**
- do not use /src/main/webapp if you are packaging jar
- add webjars-locator dependency for verson agnostic urls for jQuery.
- for jboss add webjars-locator-jboss-vfs

For cache busting
spring.resources.chain.strategy.content.enabled=true
spring.resources.chain.strategy.content.paths=/**

template Engines
- can use templates FreeMaker, Groovy, Thymeleaf, Mustache
- default location : src/main/resources/templates

Error Handling
- /error pages

Spring HATEOS
- Option 1: @EnableHypermediaSupport -- this annotation disables ObjectMapper
- Option 2: auto-config & LinkDiscoverers, ObjectMapper configured based on spring.jackson.*

CORS Support
- Cross-origin resource Sharing
- tells browsers, in a easy way, what cross-domain requests are quthorized.
- SpringMVC supports out of box
- Option1 :use @CrossOrigin - nothing more
- Option2 : bean config WebMvcConfigurerAdapter :: addCorsMappings :: registry.addMapping("/api/**")

JAX-RS and Jersey
- Endppoint classes must have @Component, @Path("/customers")
- methods has HTTP @GET
- jersey is setup as a @Bean of type ServletRegistrationBean nameed jerseyServletRegistration
- spring.jersey.servlet.load-on-startup=true
- to user Filter instead of Servlet : spring.jersey.type=filter, override jerseyFilterRegistration
- configure @Order by setting spring.jersey.filter.order.
- Servlet OR Filter registrations can be given a map of init params: spring.jersey.init.*

Security:
- add dependencies.
- default AuthenticationManager username=user password=<ramdom> in log
- Token type secuity
- OAuth type security - Authorization Serve
- OAuth - Resource Server
- OAuth Token type users
- 

SSO
- @EnableOAuth2Sso


================
SQL databaes: 
- dependency : spring-jdbc, hsqldb
- if using H2 :DB_CLOSE_ON_EXIT=FALSE
- if HSQLDB : shutdown=false
-
Connection Pooling
- Use tomcat poolig : tomcat-jdbc
spring.datasource.tomcat.max-wait=10000
spring.datasource.tomcat.max-active=50
spring.datasource.tomcat.test-on-borrow=true


DataSource config

spring.datasource.url=jdbc:mysql://localhost/test
spring.datasource.username=dbuser
spring.datasource.password=dbpass
- Driver name can be deduced from url


JdbcTeplate
- can be @Autowired
- 

Entity Classes
- traditional : persistance.xml
- @Entity public class City implements Serializable
- @Id, @GeneratedValue, @Column(nullable=false), 
- Repository/Dao : public interface CityRepository extends CrudRepository<City, Long> {

Create drop db
- only for H2/HSQL : spring.jpa.hibernate.ddl-auto=create-drop
- spring.jpa.properties.hibernate.globally_quoted_identifiers=true
- 

H2 Web console: /h2-console
- browser based, during DEV ONLY.
- when spring dev-toools is active
- spring.h2.console.enabled=true # only for dev, not for PROD
- security.user.role
- security.basic.authorize-mode
- security.basic.enabled


jOOQ 
- codegen: jooq-codegen-maven
- spring.jooq.sql-dialect=Postgres

NoSQL
- supported tech: MongoDB, Neo4J, ElasticSearch, Solr, Redis, Gemfire, Cassandra, Couchbase, LDAP

Redis : NoSQL-KVcache
- is a cache, messagebroker, rich KV-store
- redis server localhost:6379
- @autowired StringRedisTemplate

MongoDB : NOSQL-json
- json like schema
- mongodb://localhost/test
- @Autowired MongoDbFactory
- for replica set : spring.data.mongodb.uri=mongodb://user:secret@mongo1.example.com:12345,mongo2.example.com:23456/test
- @Autowired MongoTemplate
- embedded mongo dep: de.flapdoodle.embed:de.flapdoodle.embed.mongo
- spring.data.mongodb.port=27017 (if not ramdom for embedded mongo)


Neo4j Graph\
- graph db, first class relationships, connected bigdata
-localhost:7474
- @Autowired Neo4jTemplate
- spring.data.neo4j.uri=http://my-server:7474
spring.data.neo4j.username=neo4j
spring.data.neo4j.password=secret
-
NeoEmbedded
- org.neo4j:neo4j-ogm-embedded-driver
- to persist embedded: spring.data.neo4j.uri=file://var/tmp/graph.db 
- @NodeEntity rather than a JPA @Entity for City.
- spring jpa repository support: @EnableNeo4jRepositories(basePackages = "com.example.myapp.repository")
@EnableTransactionManagement
- public interface CityRepository extends GraphRepository<City>


Solr Search engines
-localhost:8983/solr
-  @Autowired SolrClient
- City is now a @SolrDocument class rather than a JPA @Entity


ElasticSearch
- distributed, realtime, search & analytics engine
- localhost:9200 
- spring.elasticsearch.jest.uris=http://search.example.com:9200
spring.elasticsearch.jest.read-timeout=10000
spring.elasticsearch.jest.username=user
spring.elasticsearch.jest.password=secret
- HttpSettingsCustomizer implements HttpClientConfigBuilderCustomizer> builder.maxTotalConnection(100).defaultMaxTotalConnectionPerRoute(5);
- for full config: define a JestClient bean.


ElasticSerach with SpringData
- @Autowired ElasticSearchTemplate
- @Autowired Client (ElasticSearch client)
- stores in app dir, 
- spring.data.elasticsearch.properties.path.home=/var/myelasticpath/
- spring.data.elasticsearch.cluster-nodes=localhost:9300,host2:9300
- City is now an Elasticsearch @Document class rather than a JPA @Entity


Cassandra :
- distributed db managment 
- @Autowired CassandraTemplate Session
- spring.data.cassandra.keyspace-name=mykeyspace
spring.data.cassandra.contact-points=cassandrahost1,cassandrahost2
- SPData repo minimal support. Annotate finder methods with @Query.


Couchbase
- Doc oriented, NOSQL, distributed, multimodel.
- spring.couchbase.bootstrap-hosts=my-host-1,192.168.1.123
spring.couchbase.bucket.name=my-bucket
spring.couchbase.bucket.password=secret
- SSL support
spring.couchbase.env.timeouts.connect=3000
spring.couchbase.env.ssl.key-store=/location/of/keystore.jks
spring.couchbase.env.ssl.key-store-password=secret
- @Aw CouchbaseTemplate

LDAP
- dep: spring-ldap-core
spring.ldap.urls=ldap://myserver:1235
spring.ldap.username=admin
spring.ldap.password=secret
- customize connection env
spring.ldap.base=
spring.ldap.base-environment=
- in memory LDAP dep: com.unboundid:unboundid-ldapsdk
spring.ldap.embedded.base-dn=dc=spring,dc=io
-initialization of ldap db, fro schema.ldif in classpath
- 


==========
Caching

- @EnableCaching
- @Cacheable("piDecimals") public int computePi() . Store result in a cache named 'piDecimals'
- Many supported - ehcache, couchbase, caffine(google)
- Cafine config
spring.cache.cache-names=foo,bar
spring.cache.caffeine.spec=maximumSize=500,expireAfterAccess=600s


========
Messaging  - JMS

ActiveMQ
spring.activemq.broker-url=tcp://192.168.1.210:9876
spring.activemq.user=admin
spring.activemq.password=secret
spring.activemq.pool.enabled=true
spring.activemq.pool.max-connections=50
- Sending Message : @AW JmsTemplate
- Receive Message: 
 @JmsListener(destination = "someQueue")
    public void processMessage(String content) {
- 

AMQP - RabbitMQ

- platform neutral, wire-level, 
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=secret
- Sending : @AW AmqpAdmin AmqpTemplate
- Queues creation: @Queue
- Recevving: @RabbitListener(queues = "someQueue") processMsg(String msg)


=======
Kafka 
- 


Kafka support:
- spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=myGroup
-Sending : @Aw KafkaTemplate
- Listening: @KafkaListener(topics = "someTopic") processMsg()
- config additional kafka props: 
spring.kafka.properties.foo.bar=baz



====
REST client
- this.restTemplate = restTemplateBuilder.basicAuthorization("user", "password").build();
- this.restTemplate.getForObject("/{name}/details", Details.class, name);
 
 
 ====
 
 Email sending
 - JavaMailSender
 spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=3000
spring.mail.properties.mail.smtp.writetimeout=5000

====
XTA - distributed transactions
- default txn log location: ./transaction-logs
- override: spring.jta.log-dir - my txn log
- 
- Supports: Automikos, Narayana.
spring.jta.atomikos.properties
spring.jta.narayana.properties


====

Spring Session - store
- Store in jdbc, mongo, Redis, Hazelcast, HashMap
spring.session.store-type=jdbc

====
JMX - montoring. 
- @ManagedResource @ManagedAttribute, @ManagedOperation

====
Testing 
- Mokito 2.0 preffered.
- dependency for integration : org.springframework:spring-test
- use @SpringBootTest instead of @ContextConfiguration, use @RunWith(SpringRunner.class)
- Options in webEnvironmentof SpringBootTest
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
- MOCK - loads WebApplicationContext, mock servlet, no container, 
- RANDOM_PORT - loads EmbeddedWebApplicationContext
- DEFINED_PORT - same with say 8080
@Transactional - will work only in MOCK. Wont work in servlet container modes, as they are in different threads.
 

- Caches ApplicationContext between tests - GOOD

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RandomPortExampleTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void exampleTest() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).isEqualTo("Hello World");
	}

}

=====

Mock Beans / SpyBeans
- @MockBean - to create mocbean to 
- use @SpyBean to wrap any existing bean with a Mockito spy.
- not required by default : @TestExecutionListeners(MockitoTestExecutionListener.class)

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTests {

    @MockBean
    private RemoteService remoteService;

    @Autowired
    private Reverser reverser;

    @Test
    public void exampleTest() {
        // RemoteService has been injected into the reverser bean
        given(this.remoteService.someCall()).willReturn("mock");
        String reverse = reverser.reverseSomeCall();
        assertThat(reverse).isEqualTo("kcom");
    }

}

Tests Auto configuration options: 
- @JsonTest - to test json serialization

JPA tests
- uses inmemory embedded db
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED) // The default is transactional
public class ExampleNonTransactionalTests {

- using @Autowired TestEntityManager - to use test outside DataJpaTests
- @AutoConfigureTestDatabase(replace=Replace.NONE) // to run tests with real db (not embedded)


JDBC Test - autconfig
@RunWith(SpringRunner.class)
@JdbcTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ExampleNonTransactionalTests {

Mongo - autoconfig

@RunWith(SpringRunner.class)
@DataMongoTest
public class ExampleDataMongoTests {


REST Client - autoconfig
@RunWith(SpringRunner.class)
@RestClientTest(RemoteVehicleDetailsService.class) //specify beans reqired for mock.
public class ExampleRestClientTest {

    @Autowired
    private RemoteVehicleDetailsService service;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void getVehicleDetailsWhenResultIsSuccessShouldReturnDetails()
            throws Exception {
        this.server.expect(requestTo("/greet/details"))
                .andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));
        String greeting = this.service.callRestService();
        assertThat(greeting).isEqualTo("hello");
    }

}

TestRestTemplate - for integration tests
- use Apache HTTPCLient 4.3.2 or later
Redirects will not be followed (so you can assert the response location)
Cookies will be ignored (so the template is stateless)
public class MyIntegrationTest {

    private TestRestTemplate template = new TestRestTemplate();

    @Test
    public void testRequest() throws Exception {
        HttpHeaders headers = template.getForEntity("http://myhost.com/example", String.class).getHeaders();
        assertThat(headers.getLocation().toString(), containsString("myotherhost"));
    }

}
-- if you are using SpringBootTest with full server (RANDOM/DEFINED_PORT), no need for hosts.
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testRequest() throws Exception {
        HttpHeaders headers = template.getForEntity("/example", String.class).getHeaders();
        assertThat(headers.getLocation().toString(), containsString("myotherhost"));
    }
    
    
 ===========================================
 Actuator - Prod feature
 
 - manage monitor app through HTTP endpoints, JMX, remote SSH, audit, healthmetrics, 
 - dependency : spring-boot-starter-actuator
 - HTTP endpoints
 actuator
 auditevents
 autoconfig
 beans
 configprops
 dump
 env - exposes @ConfigurableEnvironment
 flyway
 health
 info
 loggers - edit log config
 liquibase
 metrics
 mappings - @RequestMappings paths
 shutdown - default disabled
 trace
 
HATEOS endpoints
endpoints.hypermedia.enabled=true
- dep : org.webjars:hal-browser

CORS support 
- cross origion resource sharing
- endpoints.cors.allowed-origins=http://example.com
endpoints.cors.allowed-methods=GET,POST
endpoints.health.sensitive=false  // too much open
endpoints.health.time-to-live=5000 // to cache, to prevent DoS attacks.


Remote managemnet
management.context-path=/manage
security.user.name=admin
security.user.password=secret
management.security.roles=SUPERUSER
management.security.enabled=false //deploying behind firewall
management.port=8081
#With SSL
server.port=8443
server.ssl.enabled=true
server.ssl.key-store=classpath:store.jks
server.ssl.key-password=secret
management.port=8080
management.ssl.enabled=false

# Main server and mgmt using different keystores
server.port=8443
server.ssl.enabled=true
server.ssl.key-store=classpath:main.jks
server.ssl.key-password=secret
management.port=8080
management.ssl.enabled=true
management.ssl.key-store=classpath:management.jks
management.ssl.key-password=secret

# To prevent remote mgmt connections
management.port=8081
management.address=127.0.0.1

# Monitor using JMX
endpoints.jmx.domain=myapp
endpoints.jmx.unique-names=true
endpoints.jmx.enabled=false //To disable JMX

# Joloika - JMX over http
jolokia-core
 /jolokia
 
 jolokia.config.debug=true
 endpoints.jolokia.enabled=false // to disable
 
 
 ======
 
 Metrics - meter your service methods for charging
 
 - @Aw - CounterServices
 - this.counterService.increment("services.system.myservice.invoked");
 - metrics to message channel using metricsChannel bean.
 
 Auditing
 Tracing
 - /trace endpoint tells about past 100 requests
 
 Process Monitoring
 - can activate in META-INF/spring.factories
org.springframework.context.ApplicationListener=\
org.springframework.boot.system.ApplicationPidFileWriter,\
org.springframework.boot.actuate.system.EmbeddedServerPortFileWriter

=========

SpringBoot CLI
- brew install springboot?
- spring

spring init --list
spring init --dependencies=web,data-jpa my-project


======

Expose SpringData dao repositories as REST endpoints

- enable springmvc for this to work.
- use necessary properties spring.data.rest.* that customize the RepositoryRestConfiguration
- for more coinfig, extend RepositoryRestConfigurer

