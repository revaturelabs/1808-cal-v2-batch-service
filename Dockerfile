FROM maven:3.6.1-jdk-8
VOLUME /tmp
EXPOSE 80
COPY src/main/resources/elasticapm.properties elasticapm.properties
ADD https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.11.0/elastic-apm-agent-1.11.0.jar ./elastic-apm-agent.jar
ARG JAR_FILE
ARG SPRING_ENV
ARG DB_URL
ARG DB_USER
ARG DB_PASS
ARG CONFIG_URL
ARG APM_SERVER_URL
ARG APM_SECRET_TOKEN
ARG CLIENT_URL
ARG ENVIRONMENT_ID
ENV DT_API_URL="https://$ENVIRONMENT_ID.live.dynatrace.com/api"
ARG DT_API_TOKEN
ARG DT_ONEAGENT_OPTIONS="flavor=java"
ENV DT_HOME="/opt/dynatrace/oneagent"
RUN mkdir -p "$DT_HOME" && \
    wget -O "$DT_HOME/oneagent.zip" "$DT_API_URL/v1/deployment/installer/agent/unix/paas/latest?Api-Token=$DT_API_TOKEN&$DT_ONEAGENT_OPTIONS" && \
    unzip -d "$DT_HOME" "$DT_HOME/oneagent.zip" && \
    rm "$DT_HOME/oneagent.zip"
ENTRYPOINT [ "/opt/dynatrace/oneagent/dynatrace-agent64.sh" ]
ENV spring_profiles_active=$SPRING_ENV
ENV DB_URL=$DB_URL
ENV DB_USER=$DB_USER
ENV DB_PASS=$DB_PASS
ENV CONFIG_URL=$CONFIG_URL
ENV APM_SERVER_URL=$APM_SERVER_URL
ENV APM_SECRET_TOKEN=$APM_SECRET_TOKEN
ENV CLIENT_URL=$CLIENT_URL
ENV JAVA_OPTS="-javaagent:elastic-apm-agent.jar -Delastic.apm.server_url=$APM_SERVER_URL -Delastic.apm.secret_token=$APM_SECRET_TOKEN"
COPY src/main/resources/ojdbc7.jar .
RUN mvn install:install-file -Dfile=ojdbc7.jar -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar
COPY target/${JAR_FILE} app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /app.jar" ]
