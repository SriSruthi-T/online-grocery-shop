# Runtime Stage - Apache Tomcat with Temurin OpenJDK 11
FROM tomcat:9-jdk11-temurin

# Remove default Tomcat sample applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy pre-packaged WAR file to ROOT.war (serves at http://localhost:8080/)
COPY target/online-grocery-shop.war /usr/local/tomcat/webapps/ROOT.war

# Expose HTTP port
EXPOSE 8080

# Health check endpoint
HEALTHCHECK --interval=30s --timeout=5s --retries=3 \
    CMD curl -f http://localhost:8080/ || exit 1

# Start Tomcat server
CMD ["catalina.sh", "run"]