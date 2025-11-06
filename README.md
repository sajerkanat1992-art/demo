# demo

Dremio enables organizations to unlock the value of their data.

## Documentation

## Quickstart: How to build and run test

### (a) Prerequisites

* JDK 17 ([OpenJDK](https://adoptium.net/temurin/releases/) or Oracle)
* (Optional) Maven 3.9.3 or later
    git clone https://github.com/sajerkanat1992-art/demo.git
    cd demo
    mvn clean install -DskipTests

### (d) Run/Install

#### Run

java -jar target/demo-0.0.1.jar

Once run, the UI is accessible at:

    http://localhost:8080

Swagger : 

    http://localhost:8080/swagger-ui/index.html

### Также добавлен dockerfile и можно собрать докер образ командой

    docker build -t demo .

### И запустить командой

    docker run -d -p 8080:8080 --name demo demo
