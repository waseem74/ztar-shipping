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

# API methods:
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
 
 # Sample Request and Responses 
 
 **Success**
 ------------
  
  **Sample request:**
  
    {
        "shipmentPackage": {
          "height": {"value": 10, "measurementUnit":"cm" },
          "width":  {"value": 10, "measurementUnit":"cm" },
          "length": {"value": 20, "measurementUnit":"cm" },
          "weight": {"value": 1000, "measurementUnit":"gram" }
        },
        "carrier": {
          "carrierId": "fedex",
          "carrierServiceId": "fedexAIR",
          "shipmentServiceId": "UPS2DAY" *
        }
    }

  - All fields and values are mandatory except for carrierServiceId and shipmentServiceId (only one is required based on carrier id selected)
  - carrierId (case sensitive): ["fedex", "ups"] 
  - carrierServiceId is mandatory for fedex (case sensitive): ["fedexAIR", "fedexGroud"]
  - shipmentServiceId is mandatory for ups (case sensitive): ["UPSExpress","UPS2DAY"]
  - measurementUnit for lenght (case insensitive): ["inch", "cm"] 
  - measurementUnit for weight (case insensitive): ["pound", "gram"] 
  - measurementUnit acceptable for fedex are ["cm","gram"]
  - measurementUnit acceptable for fedex are ["inch","pound"]

  **Sample Response**
  
     { 
        "shipment-order": {
        
            "orderId": "JlsobZ",
            "orderDate": "November 22, 2021 9:25 EET",
        
            "shipmentPackage": {
                "height": {
                    "value": 10.0,
                    "measurementUnit": "cm"
                },
                
                "width": {
                    "value": 10.0,
                    "measurementUnit": "cm"
                },
                
                "length": {
                    "value": 20.0,
                    "measurementUnit": "cm"
                },
                
                "weight": {
                    "value": 1000.0,
                    "measurementUnit": "gram"
                }
            },
            
            "carrier": {
                "carrierId": "fedex",
                "carrierServiceId": "fedexAIR"
            }
        },
        
        "message": "Shipment has been placed successfully",
        "status": 200
     }
     
     -  "orderId": Order id unique value (generated value if request is successful)
     -  "orderDate": The date of shipping order request (current date generated if request is successful)
     -  "message": Response proper message
     -  "status": Http status value (200 for success)
            
 **Failure**             
 -----------
 
 - Business validation error response (Custom messages based on user locale ["es","en"])  
 
      {  
        "message": "'length' measurement unit should be cm",  
        "status": 400  
      }
  
     -  "message": Response proper message
     -  "status": Http status value (400 for failure)
     
 - Mandatory fields error response   
     {  
        "message": "{shipmentPackage.length=must not be null, shipmentPackage.weight=must not be null}",          
        "status": 400  
     }

     -  "message": Response proper message
     -  "status": Http status value (400 for failure)
     
