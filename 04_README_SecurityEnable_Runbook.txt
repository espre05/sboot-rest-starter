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

