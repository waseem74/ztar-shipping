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

# Highlited Features
The API has the following features:
- Logging is enabled, log files are generated in the root logs folder
- Customized validation messages based on shipment carrier Id chosed
- I18n is supported for english and spanish languages, locale is automatically detected from the user client tool, 
    the default language is English

# Security
The security method applied is basic authentication, it works by prompting the user for a username and password.
username: **ztar**
password: **pass123**

# Request and Response
The API has two methods:  
**welcome** (defaulted to the root url) 
  - Desc: render an internationalized welcoming message
  - URL: **{domain}/ztar-shipping**
  - HTTP Method: GET
  - Response Type: JSON
  - Sample Response:
    { "message": "Welcome to ZTAR Shipping", "status": 200 }
  
  
**createShipment** 
  - Desc: responsible for placing the shipment order and rendering a suitable status messages
  - URL: **{domain}/ztar-shipping/create-shipment ** 
  - HTTP Method: POST
  - Request Type: JSON
  - Response Type: JSON
  
