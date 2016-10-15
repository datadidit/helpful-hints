package datadidit.helpful.hints.renjin;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import datadidit.helpful.hints.renjin.model.RModel;

public class RenjinTrialResource {
	private RModel model;
	
	@POST
	@Path("model/input")
	public void GetLMInput(String x, String y){
		
	}
	
	@GET
	@Path("model")
	public RModel getRModel(){
		return null;
	}
	
	@GET
	@Path("useModel")
	public void passInSampleInput(String input){
		
	}
}
