## Renjin Karaf Example ##
***

Trying to run renjin commands in Karaf environment. But am having a lot of trouble picking up the script engine. 

## Install Instructions ##

After building project. Run the following commands inside karaf:

	feature:repo-add mvn:datadidit.helpful.hints/renjin-karaf/1.0.0-SNAPSHOT/xml/features
	feature:install datadidit-renjin-karaf
	
	

	
