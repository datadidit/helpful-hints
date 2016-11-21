package datadidit.helpful.hints.camel;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
	
	@Test
	public void ConvertCSVToJsonTestBaseball() throws Exception{
        converter = new CSVToJson(true, null);
		List<Map<?, ?>> data = converter.readObjectsFromCsv(new FileInputStream(new File("src/test/resources/Syracuse Chiefs_team_batting")));
		Map<?, ?> test = data.get(0);
		for(Map.Entry<?,?> entry : test.entrySet()){
			System.out.println("Key class "+entry.getKey().getClass());
			System.out.println("Value class "+entry.getValue().getClass());			
		}
		System.out.println(converter.writeAsJson(data));
	}
	
	//TODO: Figure out how to do this with library but for now going brute force...
	@Test
	public void ConvertCSVToJsonTestBaseballCustomHeader() throws Exception{
        converter = new CSVToJson(true, "Rk,Name,Age#NUMBER,G#NUMBER,PA#NUMBER,AB#NUMBER,R#NUMBER,H#NUMBER,2B#NUMBER,3B#NUMBER,HR#NUMBER,RBI#NUMBER,SB#NUMBER,CS#NUMBER,BB#NUMBER,SO#NUMBER,BA#NUMBER,OBP#NUMBER,SLG#NUMBER,OPS#NUMBER,TB#NUMBER,GDP#NUMBER,HBP#NUMBER,SH#NUMBER,SF#NUMBER,IBB#NUMBER,Franchise,Tm,Lg,Lvl");
		List<Map<?, ?>> data = converter.readObjectsFromCsv(new FileInputStream(new File("src/test/resources/Syracuse Chiefs_team_batting")));
		System.out.println(converter.writeAsJson(data));
	}
	
	@Test
	public void TestIsNumeric(){
		System.out.println(StringUtils.isNumeric("2000"));
		System.out.println(StringUtils.isNumeric(".2000"));
		System.out.println(".2000".substring(1));
	}
	
	@Test
	public void testMassagedJson() throws Exception{
        converter = new CSVToJson(true, null);
		List<Map<?, ?>> data = converter.readObjectsFromCsv(new FileInputStream(new File("src/test/resources/massaged.csv")));
		System.out.println(converter.writeAsJson(data));
	}
	
	@Test
	public void testIssueJson() throws Exception{
        converter = new CSVToJson(true, null);
		List<Map<?, ?>> data = converter.readObjectsFromCsv(new FileInputStream(new File("src/test/resources/break.csv")));
		System.out.println(converter.writeAsJson(data));
	}
}
