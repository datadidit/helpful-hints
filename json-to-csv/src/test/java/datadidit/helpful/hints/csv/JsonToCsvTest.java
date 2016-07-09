package datadidit.helpful.hints.csv;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class JsonToCsvTest {
	private ObjectMapper mapper; 
	
	private CsvMapper csvMapper; 
	
	@BeforeEach
	public void setup(){
		csvMapper = new CsvMapper();
		mapper = new ObjectMapper();
	}
	@Test
	public void CreateJson() throws JsonProcessingException{
		List<Simple> simpleList = new ArrayList<>();
		simpleList.add(new Simple("hello", "world", new Date()));
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(simpleList);
	
		System.out.println(json); 
		
		System.out.println("Now as CSV: ");
		CsvSchema schema = csvMapper.schemaFor(Simple.class).withHeader();
		System.out.println(csvMapper.writer(schema).writeValueAsString(simpleList));
	}
}
