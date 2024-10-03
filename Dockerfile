
FROM maven:3.9.5-amazoncorretto-21 as builder

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y descargar dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copiar el resto de los archivos de la aplicación
COPY src ./src

# Compilar la aplicación
RUN mvn package -DskipTests

FROM amazoncorretto:21

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado en la etapa de construcción
COPY --from=builder /app/target/*.jar /app/app.jar

# Especificar el comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/app.jar"]