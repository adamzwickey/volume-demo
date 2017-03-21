FROM java:8
MAINTAINER Adam Zwickey <azwickey@pivotal.io>

ADD target/volume-demo-0.1.1-SNAPSHOT.jar app.jar
VOLUME ["/home/data/"]
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Xmx512m", "-jar", "/app.jar"]
