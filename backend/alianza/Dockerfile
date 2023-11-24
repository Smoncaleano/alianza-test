# Usa una imagen base de Java 17 de OpenJDK
FROM openjdk:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR (o los archivos necesarios) a la ubicación de trabajo
COPY ./alianza-0.0.1-SNAPSHOT.jar /app/

# Comando para ejecutar la aplicación Java
CMD ["java", "-jar", "alianza-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080