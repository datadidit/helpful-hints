package datadidit.helpful.hints.csv.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

@Produces("application/csv")
public class CSVBodyWriter implements MessageBodyWriter<Object>{
	Logger logger = Logger.getLogger(CSVBodyWriter.class.getName());
	
	public long getSize(Object arg0, Class arg1, Type arg2, Annotation[] arg3,
			MediaType arg4) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isWriteable(Class arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		// TODO Auto-generated method stub
		return true;
	}

	public void writeTo(Object arg0, Class arg1, Type arg2, Annotation[] arg3,
			MediaType arg4, MultivaluedMap arg5, OutputStream arg6)
			throws IOException, WebApplicationException {
		//Whatever makes it in here should be a list
		if(arg0 instanceof List){
			List<?> myList = (List<?>) arg0;
			if(myList.isEmpty())
				logger.log(Level.WARNING, "Nothing in list to convert to CSV....");
			
			CsvMapper csvMapper = new CsvMapper();
			CsvSchema schema = null; 
			
			/*
			 * If it's not a flat POJO user must implement 
			 * CSVTransformer
			 */
			if(implementsCSVTransformer(myList.get(0).getClass())){
				Class[] params = {};
				try {
					Method meth = myList.get(0).getClass().getDeclaredMethod("toMap", params);
					
					/*
					 * Create a new list using the toMap() function
					 */
					List<Map<String, ?>> listOfMaps = new ArrayList<>();
					for(Object obj : myList){
						Map<String, ?> keyVals = (Map<String, ?>) meth.invoke(obj, params);
						
						if(schema==null)
							schema = this.buildSchemaFromKeySet(keyVals.keySet());
						
						//TODO: Keep track of the keyset if the keyset is diff throw an IOException
						listOfMaps.add(keyVals);
					}
					
					csvMapper.writer(schema).writeValue(arg6, listOfMaps);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new IOException("Unable to retrieve toMap() "+e.getMessage());
				} 
			}else{
				//TODO: Not handling empty object
				schema = csvMapper.schemaFor(myList.get(0).getClass()).withHeader();
				csvMapper.writer(schema).writeValue(arg6, myList);
			}
		}else{
			throw new IOException("Not in proper format must pass a java.util.List to use this MessageBodyWriter...");
		}
	}

	public CsvSchema buildSchemaFromKeySet(Set<String> keySet){
		Builder build = CsvSchema.builder();
		for(String field : keySet){
			build.addColumn(field);
		}
		CsvSchema schema = build.build().withHeader();		
		return schema;
	}
	
	public Boolean implementsCSVTransformer(Class arg1){
		Class[] interfaces = arg1.getInterfaces();
		for(Class aClass : interfaces){
			if(aClass.getName().equals(CSVTransformer.class.getName()))
				return true;
		}
		
		return false;
	}
}
