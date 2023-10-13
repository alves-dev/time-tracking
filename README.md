# time-tracking

O objetivo deste projeto e a minha introdução ao framework [quarkus](https://quarkus.io/).
Atualmente trabalho com Spring então vai ser interessante lidar com outra tecnologia concorrente de certa forma. 

Pretendo utilizar o `quarkus-hibernate-orm-panache` com o banco postgresql, 
o panache oferece uma forma bem diferente de lidar com a persistência e recuperação dos dados.

## Requisitos

Uma API que lida com o traqueamento do tempo:
 - Uma rota para adicionar uma atividade iniciada, pode já ter finalizada ou não
 - Uma rota para finalizar uma atividade em andamento
 - Rota que lista as atividades


### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```


### Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.


### Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/time-tracking-1.0.0-SNAPSHOT-runner`