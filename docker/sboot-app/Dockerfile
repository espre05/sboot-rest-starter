FROM store/oracle/serverjre:8
VOLUME /tmp
EXPOSE 8081
#ADD ../../target/sboot-sdrest-0.0.1-SNAPSHOT.jar app.jar
ADD sboot-sdrest-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]