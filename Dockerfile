# Imagen base de Java 23
FROM openjdk:23-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado desde el directorio `target/`
COPY target/gestion-congreso-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que correrá la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
