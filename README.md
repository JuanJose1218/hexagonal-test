# Hexagonal Test API

Proyecto backend desarrollado con **Quarkus** y **Java 21**, estructurado bajo los principios de **Arquitectura Hexagonal** (Ports and Adapters) y Clean Code.

## Requisitos Previos

* Java 21 (GraalVM o OpenJDK)
* Maven 3.9+ o el Maven Wrapper incluido (`./mvnw`)
* Docker instalado y en ejecución

---

##  Desarrollo Local


```shell script
./mvnw quarkus:dev
```

## Visualizar swagger-ui en modo dev
En modo desarrollo, puedes interactuar con los endpoints directamente desde tu navegador en:

```shell script
http://localhost:8080/q/swagger-ui/
```

## Correr aplicacion pruebas unitarias

```shell script
./mvnw clean test
```
## Despliegue con Docker
Sigue este orden de comandos para empaquetar la aplicación, preparar el entorno de red local y levantar tanto la base de datos como el contenedor de Quarkus.

1. Compilar y empaquetar el proyecto
```shell script
./mvnw clean package
```

2. Construir la imagen Docker (JVM)
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/hexagonal-test-jvm .
```

3. Crear la red local de Docker

```shell script
docker network create mi-red-local
```

4. Ejecutar el contenedor de MySQL

```shell script
docker run -d  --name contenedor-mysql --network mi-red-local -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=demo_db -p 3306:3306  mysql:8.0
```
5. Ejecutar la aplicación Quarkus
```shell script
docker run -i --rm --name app-quarkus --network mi-red-local -p 8080:8080 -e QUARKUS_DATASOURCE_JDBC_URL=jdbc:mysql://contenedor-mysql:3306/demo_db -e QUARKUS_DATASOURCE_USERNAME=root -e QUARKUS_DATASOURCE_PASSWORD=root  quarkus/hexagonal-test-jvm:latest
```