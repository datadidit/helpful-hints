package datadidit.helpful.hints.csv;

import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class JsonToCsvTest {
	private ObjectMapper mapper; 
	
	private CsvMapper csvMapper; 
	
	@Before
	public void setup(){
		csvMapper = new CsvMapper();
		mapper = new ObjectMapper();
	}
	@Test
	public void CreateJson() throws JsonProcessingException{
		csvMapper = new CsvMapper();
		mapper = new ObjectMapper();
		
		Simple simple = new Simple("hello", "world", new Date());
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(simple);
	
		System.out.println(json); 
		
		System.out.println("Now as CSV: ");
		CsvSchema schema = csvMapper.schemaFor(Simple.class).withHeader();
		System.out.println(csvMapper.writer(schema).writeValueAsString(simple));
	}
}
