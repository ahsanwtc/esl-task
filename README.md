# ESL Task

Esl Task is a web service which consumes the ESL gaming webservice and produces the contestants result regarding the cups they participated in.
  
- The service is a RESTful web service.
- Produces response in JSON format, which can be furhter consumed.
- Calls ESL's API in a non-blocking fashion.



### Version
1.0

### Tech
* [JAVA] - JAVA 1.8
* [Maven] - Maven project manager.
* [Spring] - The Spring Framework for building web applicaitons on top of JAVA EE.

### Installation / Running
ESL Task requires [Spring-Boot](https://spring.io/) v1.3.5 to run. Dependencies are managed via maven, so you just need to use `mvn`:

To build the project:
```sh
$ mvn clean
```
To run the spring-boot:
```sh
$ mvn spring-boot:run
```
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
  
  ...
 }
```

License
----

MIT

[Spring]: <https://spring.io/>
[JAVA]: <https://www.java.com/en/>
[Maven]: <https://maven.apache.org/>
   
