package datadidit.helpfulhints.provider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import datadidit.helpful.hints.renjin.rest.bean.LMInputBean;

@Consumes(MediaType.APPLICATION_JSON)
@Provider
public class BeanProvider implements MessageBodyReader<LMInputBean>{

	@Override
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2,
			MediaType arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LMInputBean readFrom(Class<LMInputBean> arg0, Type arg1,
			Annotation[] arg2, MediaType arg3,
			MultivaluedMap<String, String> arg4, InputStream arg5)
			throws IOException, WebApplicationException {
		//TODO: Make this generic and move to a util library
		System.out.println("Input is "+arg5);
		System.out.println("***************Hitting MessageBodyReader");
		
		ObjectMapper mapper = new ObjectMapper(); 
		LMInputBean bean;
		bean = mapper.readValue(arg5, LMInputBean.class);
		
		System.out.println("****************Made bean...");
		return bean; 
	}

}
