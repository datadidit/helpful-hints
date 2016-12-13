## Camel-CSV-JSON ##
***

Uses a Camel Processor to change a CSV into JSON. The Camel routes are defined using the XML DSL. You can find the routes in 'src/main/resources/META-INF/spring/camelContext.xml'. Steps to run the example are:

* Update Camel Route file. Make the 'file' endpoints reflect your environment. 
* Run 'mvn camel:run' to start camel route. 
* Drop a CSV into the 'from' directory defined in your route.

### Live Example ###
***

[![Live Demo](http://i3.ytimg.com/vi/XqxeDSi9Ww8/hqdefault.jpg)](https://www.youtube.com/watch?v=XqxeDSi9Ww8)

### Karaf Installation Notes ###

To install into karaf run the following commands:

	feature:repo-add mvn:datadidit.helpful.hints/camel-csv-json/1.0.0-SNAPSHOT/xml/features
	feature:install datadidit-camel-csv-json

Refer to this [blog](http://datadidit.com/blog/index.php/2016/07/17/an-easy-way-to-get-your-csv-into-mongo-db/) for how to use this in conjunction with the Mongo endpoint in camel. To get a CSV file into Mongo. 