package datadidit.helpful.hints.camel;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

/**
 * Camel Processor that reads in a CSV file and produces a JSON Exchange for 
 * each line. 
 * 
 * Helpful Example:
 * http://camel.apache.org/how-do-i-write-a-custom-processor-which-sends-multiple-messages.html
 * http://stackoverflow.com/questions/19766266/convert-csv-file-directly-to-json-file-using-jackson-library
 */
public class CSVToJson implements Processor{
	private Logger logger = Logger.getLogger(CSVToJson.class.getName());
	
	ProducerTemplate producer; 
	
	private Boolean header; 
	
	private Boolean fieldNames;
	
	private CsvSchema schema; 
	
	public CSVToJson(Boolean header, String fieldNames) throws Exception{
		if(!header && fieldNames!=null){
			Builder build = CsvSchema.builder();
			for(String field : fieldNames.split(",")){
				build.addColumn(field, CsvSchema.ColumnType.NUMBER_OR_STRING);
			}
			schema = build.build();
		}else if(header && fieldNames!=null && !fieldNames.equals("")){
			schema = this.buildCsvSchema(fieldNames, header);
		}else if(!header && fieldNames==null){
			throw new Exception("File must either contain headers or you must provide them..");
		}else{
    		schema = CsvSchema.emptySchema().withHeader();
		}
	}
	
	//TODO: Should probably do it this way at some point....
	private CsvSchema buildCsvSchema(String fieldNames, Boolean withHeader){
		Builder build = CsvSchema.builder();
		for(String field : fieldNames.split(",")){
			String[] fieldWithType = field.split("#");
			if(fieldWithType.length==2){
				logger.info("Field: "+fieldWithType[0]);
				logger.info("Type: "+fieldWithType[1]);
				build.addColumn(fieldWithType[0], CsvSchema.ColumnType.valueOf(fieldWithType[1]));
			}else{
				build.addColumn(field);				
			}
		}
		if(withHeader){
			return build.build().withHeader();
		}
		return build.build();
	}
	
	public void process(Exchange arg0) throws Exception {
		InputStream stream = arg0.getIn().getBody(InputStream.class);
		List<Map<?, ?>> objects = readObjectsFromCsv(stream);
	
		//Inefficient write whole files 
		/*for(Map<?,?> map : objects){
			final String json = writeAsJson(map);
			producer.send(new Processor(){
				public void process(Exchange outExchange){
					outExchange.getIn().setBody(json);
				}
			});			
		}*/
		final String json = writeAsJson(objects);
		producer.send(new Processor(){
			public void process(Exchange outExchange){
				outExchange.getIn().setBody(json);
			}
		});	
		
		//TODO:If you don't close the stream this processor will continue to try and process the exchange...
		stream.close();
	}
	
    public List<Map<?, ?>> readObjectsFromCsv(InputStream file) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        String csv = IOUtils.toString(file, "UTF-8");
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class).with(schema).readValues(csv);

        return this.fixMap(mappingIterator.readAll());
    }
    
    public List<Map<?,?>> readObjectsFromCsv(String fileContent) throws JsonProcessingException, IOException{
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class).with(schema).readValues(fileContent);
        
        return this.fixMap(mappingIterator.readAll());
    }
    
    //TODO: This is a HACK, use library or submit bug
    public List<Map<?,?>> fixMap(List<Map<?,?>> map){
    	List<Map<?,?>> newList = new ArrayList<>();
    	
     	for(Map<?, ?> entry : map){
    		Map<String,Object> newMap = new HashMap<String,Object>();
    		for(Map.Entry<?, ?> mEntry : entry.entrySet()){
    			String value = mEntry.getValue().toString();
    			//Need to remove leading . for isNumeric to work with Doubles
    			if(value.startsWith(".") && StringUtils.isNumeric(value.substring(1))){
					newMap.put(mEntry.getKey().toString(), Double.parseDouble(value));
    			}else if(StringUtils.isNumeric(mEntry.getValue().toString())){
    				newMap.put(mEntry.getKey().toString(), Integer.parseInt(value));
    			}else{
    				newMap.put(mEntry.getKey().toString(), mEntry.getValue().toString());
    			}
    		}
			newList.add(newMap);
    	}
     	
     	return newList;
    }
    
    public String writeAsJson(List<Map<?, ?>> data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
    
    /**
     * Taken from: https://itexpertsconsultant.wordpress.com/2016/08/03/how-to-readwrite-csv-file-to-map-in-java/
     * @param listOfMap
     * @param writer
     * @throws IOException
     */
    public void csvWriter(List<Map<?, ?>> listOfMap, Writer writer) throws IOException {
        CsvSchema schema = null;
        CsvSchema.Builder schemaBuilder = CsvSchema.builder();
        if (listOfMap != null && !listOfMap.isEmpty()) {
            for (Object col : listOfMap.get(0).keySet()) {
                schemaBuilder.addColumn(col.toString());
            }
            schema = schemaBuilder.build().withLineSeparator("\r").withHeader();
        }
        CsvMapper mapper = new CsvMapper();
        mapper.writer(schema).writeValues(writer).writeAll(listOfMap);
        writer.flush();
    }
    
    public String writeAsJson(Map<?, ?> data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
	
	public void setProducer(ProducerTemplate producer){
		this.producer = producer;
	}

	public Boolean getHeader() {
		return header;
	}

	public void setHeader(Boolean header) {
		this.header = header;
	}

	public Boolean getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(Boolean fieldNames) {
		this.fieldNames = fieldNames;
	}

	
}
