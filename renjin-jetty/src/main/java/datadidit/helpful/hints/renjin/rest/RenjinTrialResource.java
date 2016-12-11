package datadidit.helpful.hints.renjin.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.script.ScriptException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import datadidit.helpful.hints.renjin.impl.RenjinLinearModelImpl;
import datadidit.helpful.hints.renjin.model.RModel;
import datadidit.helpful.hints.renjin.model.RPrediction;
import datadidit.helpful.hints.renjin.rest.bean.LMInputBean;

@Path("renjin")
public class RenjinTrialResource {
	private RenjinLinearModelImpl impl = new RenjinLinearModelImpl();
	
	@GET
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response sayHello(){
		return Response.ok("Hello Renjin Resource").build();
	}
	
	@POST
	@Path("model")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public RModel addLMInput(LMInputBean bean) throws ScriptException{
		System.out.println("Bean is: "+bean);
		return impl.createModel(bean.getX(), bean.getY());
	}
	
	@GET
	@Path("model/generateRandomModel")
	@Produces({MediaType.APPLICATION_XML})
	public RModel addRandomLMInput() throws ScriptException{
		return impl.createModel(this.generateListOfRandomNumber(100), this.generateListOfRandomNumber(100));
	}
	
	@GET
	@Path("model")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public RModel getRModel(){
		return impl.getModel();
	}
	
	@GET
	@Path("model/predict")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public RPrediction passInSampleInput(@QueryParam("input") Double input) throws ScriptException{
		return impl.getLMResult(Collections.singletonList(input));
	}
	
	//TODO: Move this elsewhere 
	public List<Double> generateListOfRandomNumber(Integer listSize){
		List<Double> rando = new ArrayList<>();
		Random randomGenerator = new Random();
		
		for(int i=0; i<listSize; i++){
			rando.add(randomGenerator.nextDouble());
		}
		
		return rando;
	}
}
