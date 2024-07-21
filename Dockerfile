# Étape 1 : Construire l'application avec Maven
FROM maven:3.9.7-sapmachine-22 AS build

# Définir le répertoire de travail
WORKDIR /build

# Copier les fichiers du projet Maven
COPY pom.xml .
COPY src ./src

# Construire l'application
RUN mvn clean package -DskipTests

# Étape 2 : Créer l'image finale
FROM openjdk:17-jdk-slim


ENV MYSQL_HOST=localhost
ENV MYSQL_PORT=3306
ENV MYSQL_DATABASE=spring_db
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'étape de construction
COPY --from=build /build/target/brl-0.0.1-SNAPSHOT.jar /app/brl-0.0.1-SNAPSHOT.jar

# Spécifier la commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "brl-0.0.1-SNAPSHOT.jar"]