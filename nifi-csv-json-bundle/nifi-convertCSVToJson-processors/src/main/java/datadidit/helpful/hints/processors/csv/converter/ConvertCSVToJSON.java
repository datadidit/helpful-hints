/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package datadidit.helpful.hints.processors.csv.converter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.naming.ConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.annotation.lifecycle.OnScheduled;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.flowfile.attributes.CoreAttributes;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.ProcessorInitializationContext;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.io.OutputStreamCallback;
import org.apache.nifi.processor.util.StandardValidators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

@Tags({"csv", "json", "convert"})
@CapabilityDescription("Converts a CSV file into JSON.")
//@WritesAttributes({@WritesAttribute(attribute="", description="")})
//@SeeAlso({})
//@ReadsAttributes({@ReadsAttribute(attribute="", description="")})
public class ConvertCSVToJSON extends AbstractProcessor {
	private static final String APPLICATION_JSON = "application/json";
	
	private CsvMapper csvMapper; 
	
	private CsvSchema schema;
	
    public static final PropertyDescriptor HEADER = new PropertyDescriptor
            .Builder().name("header")
            .displayName("header")
            .description("Whether or not a header exists in the incoming CSV file.(default true)")
            .required(true)
            .allowableValues("true", "false")
            .defaultValue("true")
            .build();
    
    public static final PropertyDescriptor FIELD_NAMES = new PropertyDescriptor
            .Builder().name("Field Names")
            .displayName("Field Names")
            .description("Names of the fields in the CSV if no header exists. Field names must be in order.")
            .required(false)
            .addValidator(StandardValidators.NON_BLANK_VALIDATOR)
            .build();

    public static final Relationship REL_SUCCESS = new Relationship.Builder()
            .name("success")
            .description("Successfully converted incoming CSV file to JSON")
            .build();
    
    public static final Relationship REL_FAILURE = new Relationship.Builder()
            .name("failure")
            .description("Failed to convert incoming CSV file to JSON")
            .build();

    private List<PropertyDescriptor> descriptors;

    private Set<Relationship> relationships;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        final List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
        descriptors.add(HEADER);
        descriptors.add(FIELD_NAMES);
        this.descriptors = Collections.unmodifiableList(descriptors);

        final Set<Relationship> relationships = new HashSet<Relationship>();
        relationships.add(REL_SUCCESS);
        relationships.add(REL_FAILURE);
        this.relationships = Collections.unmodifiableSet(relationships);
       
        csvMapper = new CsvMapper();
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

    @Override
    public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return descriptors;
    }

    @OnScheduled
    public void onScheduled(final ProcessContext context) throws ConfigurationException {
    	//Retrieve properties from context
    	Boolean header = context.getProperty(HEADER).asBoolean();
    	String fieldNames = context.getProperty(FIELD_NAMES).getValue();
    
    	/*
    	 * Create Schema based on properties from user. 
    	 */
		if(!header && fieldNames!=null){
			Builder build = CsvSchema.builder();
			for(String field : fieldNames.split(",")){
				build.addColumn(field, CsvSchema.ColumnType.NUMBER_OR_STRING);
			}
			schema = build.build();
		}else if(header && fieldNames!=null && !fieldNames.equals("")){
			schema = this.buildCsvSchema(fieldNames, header);
		}else if(!header && fieldNames==null){
			throw new ConfigurationException("File must either contain headers or you must provide them..");
		}else{
    		schema = CsvSchema.emptySchema().withHeader();
		}
    }

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) throws ProcessException {
        FlowFile flowFile = session.get();
        if ( flowFile == null ) {
            return;
        }
        
        try {
            //Read in Data
        	InputStream stream = session.read(flowFile);
        	String csv = IOUtils.toString(stream, "UTF-8");
			stream.close();
			
        	//Convert CSV data to JSON
			List<Map<?,?>> objects = this.readObjectsFromCsv(csv);
			
			//Convert to JSON String
			String json = this.writeAsJson(objects);
			
			//Output Flowfile
			FlowFile output = session.write(flowFile, new OutputStreamCallback(){
				@Override
				public void process(OutputStream outputStream) throws IOException {
					IOUtils.write(json, outputStream, "UTF-8");
				}
			});
			output = session.putAttribute(output, CoreAttributes.MIME_TYPE.key(), APPLICATION_JSON);
			
			//TODO: May want to have a better default name....
			output = session.putAttribute(output, CoreAttributes.FILENAME.key(), UUID.randomUUID().toString()+".json");
			session.transfer(output, REL_SUCCESS);
        } catch (IOException e) {
        	getLogger().error("Unable to process Change CSV to JSON for this file "+flowFile.getAttributes().get(CoreAttributes.FILENAME));
			session.transfer(flowFile, REL_FAILURE);
		}
    }
    
    
    //TODO: Make a utility library or something so that Camel version and NiFi version 
    //Can just use the same code
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
    
	//TODO: Should probably do it this way at some point....
	private CsvSchema buildCsvSchema(String fieldNames, Boolean withHeader){
		Builder build = CsvSchema.builder();
		for(String field : fieldNames.split(",")){
			String[] fieldWithType = field.split("#");
			if(fieldWithType.length==2){
				getLogger().info("Field: "+fieldWithType[0]);
				getLogger().info("Type: "+fieldWithType[1]);
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
}
