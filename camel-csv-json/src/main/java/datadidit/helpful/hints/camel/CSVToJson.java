package datadidit.helpful.hints.camel;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.io.IOUtils;

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
	ProducerTemplate producer; 
	
	private Boolean header; 
	
	private Boolean fieldNames;
	
	private CsvSchema schema; 
	
	public CSVToJson(Boolean header, String fieldNames) throws Exception{
		if(!header && fieldNames!=null){
			Builder build = CsvSchema.builder();
			for(String field : fieldNames.split(",")){
				build.addColumn(field);
			}
			schema = build.build();
		}else if(!header && fieldNames==null){
			throw new Exception("File must either contain headers or you must provide them..");
		}else{
    		schema = CsvSchema.emptySchema().withHeader();
		}
	}
	
	public void process(Exchange arg0) throws Exception {
		InputStream stream = arg0.getIn().getBody(InputStream.class);
		List<Map<?, ?>> objects = readObjectsFromCsv(stream);
	
		for(Map<?,?> map : objects){
			//Create JSON 
			final String json = writeAsJson(map);
			producer.send(new Processor(){
				public void process(Exchange outExchange){
					outExchange.getIn().setBody(json);
				}
			});			
		}
		
		//TODO:If you don't close the stream this processor will continue to try and process the exchange...
		stream.close();
	}
	
    public List<Map<?, ?>> readObjectsFromCsv(InputStream file) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        String csv = IOUtils.toString(file, "UTF-8");
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class).with(schema).readValues(csv);

        return mappingIterator.readAll();
    }
    
    public List<Map<?,?>> readObjectsFromCsv(String fileContent) throws JsonProcessingException, IOException{
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class).with(schema).readValues(fileContent);

        return mappingIterator.readAll();
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
