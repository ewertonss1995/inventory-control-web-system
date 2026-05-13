# Estágio 1: Build (Compilação)
# Usamos uma imagem completa do Maven para compilar o código
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia apenas o pom.xml primeiro para baixar as dependências (otimiza o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e gera o .jar (pulando testes para agilizar o lab)
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Execução)
# Usamos uma imagem JRE leve (Alpine ou Distroless) para diminuir a superfície de ataque
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Criamos um usuário do sistema para não rodar a aplicação como root (Segurança)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiamos apenas o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8080

# Configurações de memória otimizadas para contêineres
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
