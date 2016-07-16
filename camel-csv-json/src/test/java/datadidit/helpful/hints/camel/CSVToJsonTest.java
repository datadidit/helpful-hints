package datadidit.helpful.hints.camel;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CSVToJsonTest {
	private CSVToJson converter; 
	
	@Test
	public void ConvertCSVToJsonTestHeader() throws Exception{		
        converter = new CSVToJson(true, null);
		List<Map<?, ?>> data = converter.readObjectsFromCsv(new FileInputStream(new File("src/test/resources/WithHeader.json")));
		System.out.println(converter.writeAsJson(data));
	}
	
	@Test
	public void ConvertCSVToJsonTestNoHeader() throws Exception{
		converter = new CSVToJson(false, "firstName,lastName,dob");
		
        List<Map<?, ?>> data = converter.readObjectsFromCsv(new FileInputStream(new File("src/test/resources/NoHeader.json")));
		System.out.println(converter.writeAsJson(data));
	}
}
