FROM openjdk:11-jdk
COPY CashOverflow/target/CashOverflow-0.0.1-SNAPSHOT.jar /usr/local/lib/CashOverflow-0.0.1-SNAPSHOT.jar
EXPOSE 9001
ENTRYPOINT [ "java", "-jar", "/usr/local/lib/CashOverflow-0.0.1-SNAPSHOT.jar" ]