# spring-boot-performance-workshop

Prerequisites:
- Java 17
- Maven
- Docker client

To properly start the app, first run the ```docker-compose.yml``` file and fire up the **MongoDB** and **Wiremock** instances.
Now you can start the app by running the Main class.

To run a performance test, tune your params in ```GatlingSimulation``` and then run ```mvn gatling:test``` from the ```gatling``` folder 