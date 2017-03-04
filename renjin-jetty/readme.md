## Renjin Rest Example ##

### Pre-reqs ###

* This is a maven project so you'll need maven installed on your system 
* Follow Renjin instructions so pom can download renjin dependencies: http://docs.renjin.org/en/latest/library/project-setup.html#maven

### Live Example ###
***

[![Live Demo](http://i3.ytimg.com/vi/VVrCd30JKZ8/hqdefault.jpg)](https://www.youtube.com/watch?v=VVrCd30JKZ8)

### Usage ###
***

* Start maven
	
	mvn jetty:run
	
* Browse to welcome url: http://localhost:8080/renjin 

* To just generate a sample model use this endpoint: http://localhost:8080/renjin/model/generateRandomModel

* To get a prediction from the sample model: http://localhost:8080/renjin/model/predict?input=10

* To see your model again: http://localhost:8080/renjin/model

* To create a model from your own sample data you'll need to perform a POST. To see a sample POST refer to the sample file in 'src/test/resources/sampleModelPost' . 
Sample was created from using REST Client. If you have that utility handy just import the example. 


	