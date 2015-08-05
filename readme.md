![G2W Microservices](https://confluence.ops.expertcity.com/download/attachments/72604993/image2014-12-19%2015%3A50%3A8.png?version=1&modificationDate=1419033007811&api=v2)

<!--
###############################################################################
#   ___ _____      __  __  __ ___                            _
#  / __|_  ) \    / / |  \/  |_ _|__ _ _ ___ ___ ___ _ ___ _(_)__ ___ ___
# | (_ |/ / \ \/\/ /  | |\/| || |/ _| '_/ _ (_-</ -_) '_\ V / / _/ -_|_-<
#  \___/___| \_/\_/   |_|  |_|___\__|_| \___/__/\___|_|  \_/|_\__\___/__/
#
###############################################################################
-->

G2W APi Proxy (Zuul)
====================

Zuul proxy server using spring cloud.
Please email any suggestions and correction to g2w-microservices@citrix.com

See:
[Zuul Spring Cloud](http://projects.spring.io/spring-cloud/spring-cloud.html#_router_and_filter_zuul)
[Netflix Zuul](https://github.com/Netflix/zuul/wiki)

To run
------

    $ mvn clean install
    $ java -jar target/apiproxyserver-*.jar --eureka.password=password
    or
    $ java -jar target/apiproxyserver-*.jar --port=7070 --eureka.password=password

    
**Startup Options:**

|   Description                     |   Default                 |   Startup option  |
|   --------------------------------|   ------------------------|   ----------------|
|   Microservice port               |   random                  |   --port=xxxx     |
|   Management Endpoints port       |   uses same port as app   |   none            |
|   Eureka Server Password          |   none                    |   --eureka.password=xxxx NOTE: this needs to match the regdiscovery-microservice password  |



Usage
-----

To look at all the registered API mappings to backend services:
**Use:** http://localhost:7070/mappings


Logging
=======

Spring boot uses Logback by default. Logback succeeds and is the successor to log4j.
See [Logback Project](http://logback.qos.ch/)

To log in classes simple use:

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    ...

    Logger logger = LoggerFactory.getLogger(MuClass.class);
    logger.info("mu log statement);

To configure logging include a logback.xml file in src/main/resources . It will be picked up automatically.


Configuration
=============

The Microservice scaffold should provide sensible defaults. If you wish to override configuration do so in the
application.yml file.
See [Common application properties](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)


Predifined Endpoints (APIs)
==========================

Accessible at random port chosen at startup (default port, change with startup argument '--managementPort=9320')

See [Spring Actuator](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-enabling) for more info

Sensitive is true means endpoint can only be access through trusted IPs


|   ID              |   Description     |   Sensitive  |
|   ----------------|   ----------------|   -----------|
|   autoconfig      |   Displays an auto-configuration report showing all auto-configuration candidates and the reason why they ‘were’ or ‘were not’ applied.   |   true    |
|   beans           |   Displays a complete list of all the Spring Beans in your application.   |  true |
|   configprops     |   Displays a collated list of all @ConfigurationProperties.   |   true    |
|   dump            |   Performs a thread dump. |   true    |
|   env             |   Exposes properties from Spring’s ConfigurableEnvironment.   |   true    |
|   health          |   Shows application health information (a simple ‘status’ when accessed over an unauthenticated connection or full message details when authenticated).   |   false    |
|   info            |   Displays arbitrary application info.    |   false   |
|   metrics         |   Shows ‘metrics’ information for the current application.    |   true    |
|   mappings        |   Displays a collated list of all @RequestMapping paths.    |   true  |
|   shutdown        |   Allows the application to be gracefully shutdown (not enabled by default).  |   true    |
|   trace           |   Displays trace information (by default the last few HTTP requests). |   true    |
|   hystrix.stream  |   A stream of circuit breakers status. This is consumed by the Circuit Breaker Dashboard | true  |



Health Check
------------

**/health**

The following health Indicators are configured automatically when appropriate:

|   Health Indicator            |   Description                 |
|   ----------------------------|   ----------------------------|
|   DiskSpaceHealthIndicator    |   Checks for low disk space.  |
|   DataSourceHealthIndicator   |   Checks that a connection to DataSource can be obtained. |
|   MongoHealthIndicator        |   Checks that a Mongo database is up. |
|   RabbitHealthIndicator       |   Checks that a Rabbit server is up.  |
|   RedisHealthIndicator        |   Checks that a Redis server is up.   |
|   SolrHealthIndicator         |   Checks that a Solr server is up.    |

You can customize health indicators to applicable things to your microservice. Please see [Writing Custom Health Indicators](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#_writing_custom_healthindicators)


