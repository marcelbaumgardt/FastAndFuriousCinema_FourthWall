# FastAndFuriousCinema_FouthWall

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

1) Rename project ...
2) Change .properties to .yaml
3) Add daily limit to open movie database connector
4) Add more detailed description in Controllers (Swagger)
5) Consider extract insertDate and modificationDate to external class
6) Add logging to services
7) Upgrade methods in OpenMovieDatabaseResponseMapper

* mapToBoxOfficePairCurrencyValue
* unifyDoubleValue

8) Add hints to queries, simillar to mssql (with(index,nolock))
9) Add Dockerfile and docker-compose.yml
10) Add pipeline and automaticly update project version
11) Add prometheus metrics
12) Increase cover ration
13) Add OWASP library to pipeline (or sth simillar)
14) Change extension of integration tests to .kt
15) Add documentation .adoc
16) Add security endpoints (restart password etc.)
17) Check cyclic dependencies
18) Analyze dependencies (remove unused, resolve conflicts)
19) Check plan of sql queries
20) Analyze app with JMeter