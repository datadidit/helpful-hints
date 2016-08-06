package datadidit.helpful.hints.csv.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import datadidit.helpful.hints.csv.provider.CSVTransformer;
import datadidit.helpful.hints.csv.test.model.ComplexSample;
import datadidit.helpful.hints.csv.test.model.SimpleSample;


@Path("CustomProvider")
public class CXFSampleResource {
	@GET
	@Path("help")
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response sayHello(){
		return Response.ok("HELP").build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, "application/csv"})
	@Path("test/{caseToUse}")
	public List<?> doSomething(@PathParam("caseToUse") @DefaultValue("simple") String caseToUse){
		List<Object> test = new ArrayList<>();
		//TODO: Below is a hack should be cleaner way to get this done
		if(caseToUse.equalsIgnoreCase("simple")){
			for(SimpleSample samp : this.generateSimpleSample())
				test.add(samp);
		}else{
			for(ComplexSample samp : this.generateComplexSample())
				test.add(samp);
		}
		
		System.out.println("Hello: "+test);
		return test;
	}
	
	public List<SimpleSample> generateSimpleSample(){
		List<SimpleSample> samples = new ArrayList<>();
		samples.add(new SimpleSample("hello", "world", new Date()));
		samples.add(new SimpleSample("hello", "chad", new Date()));
		samples.add(new SimpleSample("hello", "marcus", new Date()));
		samples.add(new SimpleSample("hello", "joy", new Date()));
		samples.add(new SimpleSample("hello", "mom", new Date()));
		
		return samples;
	}
	
	public List<ComplexSample> generateComplexSample(){
		Map<String, String> grades = new HashMap<>();
		grades.put("Class1", "A");
		grades.put("Class2", "B");
		grades.put("Class3", "C");
		grades.put("Class4", "D");
		
		List<ComplexSample> samples = new ArrayList<>();
		samples.add(new ComplexSample(UUID.randomUUID().toString(), grades));
		samples.add(new ComplexSample(UUID.randomUUID().toString(), grades));
		samples.add(new ComplexSample(UUID.randomUUID().toString(), grades));
		samples.add(new ComplexSample(UUID.randomUUID().toString(), grades));
		
		return samples;
	}
}
