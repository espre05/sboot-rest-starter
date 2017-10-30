Adding dep
- spring-boot-starter-security


Authorizing ADMIN  - PersonRepository
- for save New Person, 
- for delete Person.

Since we added security on new Person. Securley init data
- see Application::securlyInsertInitData()


Test out security

GET Persons must be accessible by all  - Unauthorized! in browser I have to enter user/user for auth. It should give data without auth
curl http://localhost:8080/api/v1/persons  

TROBLE SHOOT - Returns data, but need to GET without uid/pwd
curl user:user@localhost:8080/api/v1/persons  


#https enable springboot
 #For prod buy certificate? - the free automated one? 
 # For dev generate
 keytool -genkey -alias sboot -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
 password: admin123
 ab, cd,ef,gh,ij, ca, yes
 src/main/resources/keystore.p12
 
 
 # https enable keycloak OAuth2 server
 keytool -genkey -alias localhost -keyalg RSA -keystore keycloak.jks -validity 10950
    Enter keystore password: secret
    Re-enter new password: secret
    What is your first and last name?
    [Unknown]:  localhost
    What is the name of your organizational unit?
    [Unknown]:  Keycloak
    What is the name of your organization?
    [Unknown]:  Red Hat
    What is the name of your City or Locality?
    [Unknown]:  Westford
    What is the name of your State or Province?
    [Unknown]:  MA
    What is the two-letter country code for this unit?
    [Unknown]:  US
    Is CN=localhost, OU=Keycloak, O=Test, L=Westford, ST=MA, C=US correct?
    [no]:  yes
    
 