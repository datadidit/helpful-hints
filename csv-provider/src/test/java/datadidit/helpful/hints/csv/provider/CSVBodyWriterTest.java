package datadidit.helpful.hints.csv.provider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;

import datadidit.helpful.hints.csv.test.model.ComplexSample;
import datadidit.helpful.hints.csv.test.model.SimpleSample;

public class CSVBodyWriterTest {
	private CSVBodyWriter writer; 
	
	private ByteArrayOutputStream stream;

	@Before
	public void setup(){
		writer = new CSVBodyWriter();
		stream = new ByteArrayOutputStream();
	}
	
	@Test
	public void SimpleWriteToTest() throws WebApplicationException, IOException{		
		List<SimpleSample> samples = new ArrayList<>();
		samples.add(new SimpleSample("hello", "world", new Date()));
		samples.add(new SimpleSample("hello", "chad", new Date()));
		samples.add(new SimpleSample("hello", "marcus", new Date()));
		samples.add(new SimpleSample("hello", "joy", new Date()));
		samples.add(new SimpleSample("hello", "mom", new Date()));
		
		writer.writeTo(samples, List.class, null, null, null, null, stream);
		
		System.out.println(stream.toString());
	}
	
	@Test
	public void ComplexWriteToTest() throws WebApplicationException, IOException{
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
		
		writer.writeTo(samples, List.class, null, null, null, null, stream);
		
		System.out.println(stream.toString());
	}
}
