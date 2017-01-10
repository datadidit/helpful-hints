Import CSV

	d3.csv('data/cities.csv', function(data){console.log(data)})

Import JSON
	
	d3.csv('data/tweets.json', function(data){console.log(data)})
	
Example of Putting your data into a global variable 

	var cities = null
	d3.csv('data/tweets.json', function(data){ cities = data})
	cities
	
d3.scale.linear = d3.scaleLinear -> var newRamp = d3.scaleLinear().domain([500000, 13000000]).range([0, 500])
d3.scale.quantile = d3.scaleQuantile -> var qScale = d3.scaleQuantile().domain(sampleArray).range([0, 1, 2])

How to add a Circle:

d3.select("svg")
.append("circle")
.attr("r", 20)
.attr("cx",20)
.attr("cy",20)
.style("fill","red");

d3.select("svg")
.selectAll("rect")
.data([12])
.enter()
.append("rect")
.attr("x", "20")
.attr("y", "20")
.attr("width", "100")
.attr("height", "20")


d3.select("svg")
.selectAll("rect")
.data([12])
.append("rect")
.attr("width", "10")
.attr("height", "20")

d3.select("svg")
 .selectAll("rect")
 .data(randomData)
 .enter()
 .append("rect")
 .attr("width", 10)
 .attr("height", function(d) {return d;});