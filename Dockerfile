# Build Stage - Maven kullanarak uygulamayı derlemek için kullanılır
FROM maven:3.8.5-openjdk-17 AS build

# WORKDIR: Docker konteynerinde bir çalışma dizini oluşturur veya var olan bir dizine geçer.
# Eğer dizin yoksa, WORKDIR komutu bu dizini otomatik olarak oluşturur.
# /app: Bu, çalışma dizinini belirlediğimiz yoldur.
# /app konteyner içinde belirtilen dizindir ve her RUN, CMD, ENTRYPOINT, COPY, ADD gibi komutlar bu dizin içinde çalışır.
WORKDIR /app

# Proje dosyalarını kopyalar container içinde oluşan /app isimli çalışma dizinine kopyalar
COPY . .

# Maven ile projeyi derler jar oluşturur
RUN mvn clean install -DskipTests

# Final Stage - JDK'yi kullanarak yalnızca JAR'ı alıp çalıştırmak için kullanılır
FROM openjdk:17 AS deploy

# Derlenmiş JAR dosyasını alır. (multi-stage build) kullanırken, önceki aşamalardan dosya kopyalamak için kullanılır.
COPY --from=build /app/target/*.jar app.jar

# Uygulama çalıştırma komutu
ENTRYPOINT ["java", "-jar", "/app.jar"]








# Localde clean install yapılırsa aşağıdaki kısım yeterli oluyor.

#FROM openjdk:17
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]