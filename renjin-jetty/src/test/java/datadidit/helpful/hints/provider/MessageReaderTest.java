package datadidit.helpful.hints.provider;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import datadidit.helpful.hints.renjin.rest.bean.LMInputBean;

public class MessageReaderTest {
	@Test
	public void test() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper(); 
		LMInputBean bean;
		bean = mapper.readValue(new File("src/test/resources/sample.json"), LMInputBean.class);
		System.out.println(bean);
	}
}
