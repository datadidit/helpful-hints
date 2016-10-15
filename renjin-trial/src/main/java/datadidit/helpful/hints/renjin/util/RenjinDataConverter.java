package datadidit.helpful.hints.renjin.util;

import java.util.ArrayList;
import java.util.List;

import org.renjin.sexp.DoubleArrayVector;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.StringVector;
import org.renjin.sexp.Vector;

import datadidit.helpful.hints.renjin.model.InputOutput;
import datadidit.helpful.hints.renjin.model.RModel;
import datadidit.helpful.hints.renjin.model.RPrediction;

public class RenjinDataConverter {
	public RenjinDataConverter(){
		
	}
	
	public RModel convertListVectorToRModel(ListVector model, ListVector summaryModel){
		//Need to figure out how to deal with ListVector to get information you need
		//System.out.println(getElementNames(summaryModel));
		
		Vector coefficients = model.getElementAsVector("coefficients");
		Double intercept = coefficients.getElementAsDouble(0);
		Double slope = coefficients.getElementAsDouble(1);
		RModel rModel = new RModel();
		rModel.setIntercept(intercept);
		rModel.setSlope(slope);
		rModel.setFormula(model.getElementAsString(11));
		rModel.setRSquared(summaryModel.getElementAsDouble("r.squared"));
		rModel.setAdjustedRSquared(summaryModel.getElementAsDouble("adj.r.squared"));
		return rModel;
	}
	
	public RPrediction createRPrediction(List<Double> inputs, DoubleArrayVector results){
		RPrediction prediction = new RPrediction();
		for(int i=0; i<inputs.size(); i++){
			InputOutput obj = new InputOutput(); 
			obj.setInput(inputs.get(i));
			obj.setOutput(results.get(i));
			
			prediction.addResult(obj);
		}
		
		return prediction;
	}
	
	private List<String> getElementNames(ListVector vector){
		List<String> names = new ArrayList<>();
		StringVector namesVector = vector.getAttributes().getNames();
		
		for(String name : namesVector){
			names.add(name);
		}
		
		return names; 
	}
}
