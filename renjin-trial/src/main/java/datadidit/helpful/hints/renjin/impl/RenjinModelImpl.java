package datadidit.helpful.hints.renjin.impl;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.renjin.sexp.DoubleArrayVector;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.StringVector;

import datadidit.helpful.hints.renjin.model.RModel;

public class RenjinModelImpl {
	private ScriptEngine engine;
	
	public RenjinModelImpl(){
		//TODO: Make sure you compile with 1.8
    	// create a script engine manager:
        ScriptEngineManager manager = new ScriptEngineManager();
        
        // create a Renjin engine:
        engine = manager.getEngineByName("Renjin");
        
        // check if the engine has loaded correctly:
        if(engine == null) {
            throw new RuntimeException("Renjin Script Engine not found on the classpath.");
        }	
	}
	
	public RModel getModel(List<Double> x, List<Double> y) throws ScriptException{
		double[] xR;
		engine.put("x", this.convertListToArray(x));
		engine.put("y", this.convertListToArray(y));
		
		ListVector model = (ListVector) engine.eval("lm(x ~ y)");
		
		RModel myRModel = new RModel(model);
		System.out.println(model.getNames());
		int index = 0;
		for(SEXP p : model){
			//Taken from AbstractSEXP
			System.out.println(p.getNames());
			System.out.println(p);
			index++;
		}
		
		return myRModel;
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
