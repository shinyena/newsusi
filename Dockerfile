FROM tomcat:8.5.46-jdk8-openjdk
RUN rm -Rf /usr/local/tomcat/webapps/ROOT ## tomcat root 경로 삭제
COPY ./war/ROOT.war /usr/local/tomcat/webapps/ROOT.war