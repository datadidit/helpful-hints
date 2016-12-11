package datadidit.helpful.hints.renjin.impl;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.renjin.sexp.DoubleArrayVector;
import org.renjin.sexp.ListVector;

import datadidit.helpful.hints.renjin.RenjinLinearModel;
import datadidit.helpful.hints.renjin.model.RModel;
import datadidit.helpful.hints.renjin.model.RPrediction;
import datadidit.helpful.hints.renjin.util.RenjinDataConverter;

public class RenjinLinearModelImpl implements RenjinLinearModel {
	ScriptEngine engine;
	
	RenjinDataConverter converter; 
	
	RModel model;
	
	public RenjinLinearModelImpl(){
		//TODO: Make sure you compile with 1.8
    	// create a script engine manager:
        ScriptEngineManager manager = new ScriptEngineManager();
        
        // create a Renjin engine:
        engine = manager.getEngineByName("Renjin");
        
        // check if the engine has loaded correctly:
        if(engine == null) {
            throw new RuntimeException("Renjin Script Engine not found on the classpath.");
        }	
        
        converter = new RenjinDataConverter(); 
	}
	
	public RModel createModel(List<Double> x, List<Double> y) throws ScriptException{
		engine.put("x", this.convertListToArray(x));
		engine.put("y", this.convertListToArray(y));
		engine.eval("model = lm(y ~ x)");
		
		//TODO: Summary has what I need not sure I'll be using model
		ListVector listRepOfModel = (ListVector) engine.eval("model");
		ListVector modelSum = (ListVector) engine.eval("summary(model)");
		
		this.model = converter.convertListVectorToRModel(listRepOfModel, modelSum);
		
		return this.model;
	}
	
	public RModel getModel(){
		return this.model;
	}
	
	public RPrediction getLMResult(List<Double> x) throws ScriptException{
		if(engine.eval("model") != null){
			engine.put("x", this.convertListToArray(x));
			DoubleArrayVector dbVector = (DoubleArrayVector) engine.eval("predict(model, data.frame(x))");
			return converter.createRPrediction(x, dbVector);
		}
		
		return null;
	}
	
	private double[] convertListToArray(List<Double> list){
		double[] temp = new double[list.size()];
		
		int i=0;
		for(i=0; i<list.size(); i++){
			temp[i] = list.get(i);
		}
		
		return temp;
	}
}
