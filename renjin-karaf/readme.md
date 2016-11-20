## Renjin Karaf Example ##
***

Trying to run renjin commands in Karaf environment. But am having a lot of trouble picking up the script engine. 

## Install Instructions ##

After building project. Run the following commands:

	feature:repo-add mvn:datadidit.helpful.hints/renjin-karaf/1.0.0-SNAPSHOT/xml/features
	feature:install extended-renjin-karaf
	feature:install renjin-karaf
	
Above should load into karaf 4.0.7 just fine. Running the following command produces the error:

	install -s mvn:datadidit.helpful.hints/renjin-karaf/1.0.0-SNAPSHOT
	

	
