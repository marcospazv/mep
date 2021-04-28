# Use the AWS Elastic Beanstalk Glassfish image
FROM        amazon/aws-eb-glassfish:5.0-al-onbuild-2.11.1
# Exposes port 8080
EXPOSE      8080
# Install Datasource dependencies and configure datasource in Glassfish
RUN         curl -L -o /usr/local/glassfish5/glassfish/domains/domain1/lib/mysql-connector-java-8.0.23.jar https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.23 && \
            asadmin start-domain domain1 && \
            asadmin create-jdbc-connection-pool --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --restype javax.sql.DataSource --property url=jdbc:mysql://mysql.caiy9ugrkuu2.us-east-1.rds.amazonaws.com:3306/bd_mep?zeroDateTimeBehavior=convertToNull:user=admin:password=Mp1sa0900:serverName=mysql.caiy9ugrkuu2.us-east-1.rds.amazonaws.com:portNumber=3306:databaseName=bd_mep:useSSL=false mepPool && \
            asadmin create-jdbc-resource --connectionpoolid mepPool jdbc/mep