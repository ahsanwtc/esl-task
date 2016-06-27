# ESL Task

Esl Task is a web service which consumes the ESL gaming webservice and produces the contestants result regarding the cups they participated in.
  
- The service is a RESTful web service.
- Produces response in JSON format, which can be furhter consumed.
- Calls ESL's API in a non-blocking fashion.

### Project Structure
The project one main package `me.aboutjaved` and two sub packages `ESLGaming` and `Service`.

    |-- README.md
    |-- pom.xml
    |-- .gitignore
    |-- LICENSE.rst
    |-- src
        |-- java/me/aboutjaved
            |-- ESLGaming
                |-- CupList.java
                |-- Contestant.java
                |-- Cup.java
                |-- Ranking.java
                |-- Team.java
                |-- TeamResult.java
            |-- Service
                |-- CupListInterface.java
                |-- CupListService.java
            |-- Application.java
            |-- AsyncController.java
The `ESLGaming` package contains classes for serialization and deserialization of data. `Service` package deals with the calling of the API.

### Assumptions
Following assumptions are taken while working on this project:
1. The end result is required to be a JSON object, the service was assumed to be a web service.
2. The significance of `teamSize` wasn't clear from the problem statement, it wasn't used in the calculations.
### Version
1.0
### Tech
* [JAVA] - JAVA 1.8
* [Maven] - Maven project manager.
* [Spring] - The Spring Framework for building web applicaitons on top of JAVA EE.

### Installation and Usage
ESL Task requires [Spring-Boot](https://spring.io/) v1.3.5 to run. Dependencies are managed via maven, so you just need to use `mvn`:
* Clone the repo: `git clone git@github.com:ahsanwtc/esl-task.git`
* Go into the directory: `cd esltask`
* Build the project and dependencies: `mvn clean`
* Start the Spring-Boot to run the applicaiton: `mvn spring-boot:run`
* Visit `http://localhost:8080` to view service.

After running the boot, the service will be available at `http://localhost:8080`. A normal response looks like:
```json
{
  "719263": {
    "cupsPlayed": 1,
    "bestPosition": 2,
    "worstPosition": 2
  },
  "781625": {
    "cupsPlayed": 3,
    "bestPosition": 7,
    "worstPosition": 41
  }
 }
```


License
----

MIT

[Spring]: <https://spring.io/>
[JAVA]: <https://www.java.com/en/>
[Maven]: <https://maven.apache.org/>
   
