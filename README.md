# ZTAR Shipping
ztar-shipping is a Rest API that is used to create a shipment order by passing the shipment carrier ID that the user would like to use, and the set of parameters needed to get the shipment profile created with the shipping service itself.

# Technology used
The API was build using spring boot platform with the following maven dependencies:

-  spring-boot-starter-web
-  spring-boot-starter-tomcat
-  spring-boot-starter-validation
-  spring-boot-starter-test
-  spring-boot-devtools
-  jackson-dataformat-xml
-  commons-lang3

# Running the application
You can either run the application directly by running the ZtarShippingApplication class, or you can generate a war file
using the following mvn command: 
**mvn clean install**
Then you can grap the war file generated in the target folder and deploy it directly to your tomcat server instance.

