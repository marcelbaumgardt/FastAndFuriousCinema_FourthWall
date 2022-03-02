# FastAndFuriousCinema_FourthWall

FourthWall - Backend Coding Challenge

BEFORE RUN

create postgres database

* port : 5432
* database : fafc4w
* username : postgres
* password : postgres
* schemas : [public,dbo]

RUN

* Run project with profile "local"

* with command : mvn spring-boot:run -Dspring-boot.run.profiles=local -DOMD_API_KEY={yourApiKey}
* to run tests use command : mvn clean install -Dspring-boot.run.profiles=local -DOMD_API_KEY={yourApiKey}

SWAGGER

* local address : http://localhost:8080/swagger-ui.html

* to log in use /login endpoint with credential
* username : user, password : test (role: USER)
* username : admin, password : test (role: ADMIN)
* then copy bearer token (for example : Bearer
  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjQ2MTg0MzMzfQ.tdKKCLOEy5or13KXkyWQxRIopdDfMAB57O_gFXpXXtc)
* and paste to Authorization input at the top of page

THE CHOICE I'VE MADE

1) Spring/Spring Boot - I use it in my daily work
2) Kotlin/Java - Kotlin is not my first choice but I used it to learn a bit at the same time. I had a problem with tests
   in Kotlin so I wrote them in Java
3) Feign client - Easy to read and understand
4) Spring Security - JWT Token - Easy to configure
5) Maven
6) Postgres
7) Flyway

TODO

1) Change .properties to .yaml
2) Add daily limit to open movie database connector
3) Add more detailed description in Controllers (Swagger)
4) Consider extract insertDate and modificationDate to external class
5) Add logging to services
6) Add day enum instead int value
7) Upgrade methods in OpenMovieDatabaseResponseMapper

* mapToBoxOfficePairCurrencyValue
* unifyDoubleValue

7) Add hints to queries, simillar to mssql (with(index,nolock))
8) Add Dockerfile and docker-compose.yml
9) Add pipeline and automaticly update project version
10) Add prometheus metrics
11) Increase cover ration
12) Add OWASP library to pipeline (or sth simillar)
13) Change extension of integration tests to .kt
14) Add documentation .adoc
15) Add security endpoints (restart password etc.)
16) Check cyclic dependencies
17) Analyze dependencies (remove unused, resolve conflicts)
18) Check plan of sql queries
19) Analyze app with JMeter