mvn clean package -DskipTests
mv ./target/image-repository-0.0.1-SNAPSHOT.jar ./docker/java
printf "copy file success\n"