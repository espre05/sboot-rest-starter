#########3 start #########
mvn spring-boot:run
curl http://localhost:8080/

#Create deployable jar 
# add spring-boot build plugin
mvn package 

#boot run
export MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=128M
mvn spring-boot:run
# or with gradle gradle bootRun

# Run on macOS in clear Terminal
clear && printf '\e[3J' && mvn spring-boot:run



# Jar run
java -jar target/insurance-0.0.1-SNAPSHOT.jar  [--debug ] [--trace]


## standalone run with remote debug
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
       -jar target/myproject-0.0.1-SNAPSHOT.jar




#############





serves RESOURCES at /
spring.data.rest.basePath=/api/v1

##### Troubleshoot ####
1. /people was not picked up. 

Resolution: the PersonRepository NEEDs to be in an inner pacckage than Application!

 

 
 
############################ Test #####
Test in browser: 

http://localhost:8080/persons

# Must see the endpoints
curl http://localhost:8080
# Must lst the people
curl http://localhost:8080/persons


Test by posting new data: 

curl -X POST -i -H "Content-Type:application/json" -d '{"firstName":"Greg", "lastName":"Turnquist"}' http://localhost:8080/persons
# Must lst the new people
curl http://localhost:8080/people

Access H2 console and verify
- ensure dev tools enabled
- ensure console enabled
- http://localhost:8080/h2-console -- works

#Check page size working -- works
http://localhost:8080/persons/?size=2
http://localhost:8080/persons/search/lnameStartsWith?name=S
http://localhost:8080/persons/search/lnameStartsWith?name=S&sort=name,desc

Check HAL browser
http://localhost:8080
http://localhost:8080/browser/index.html#/

