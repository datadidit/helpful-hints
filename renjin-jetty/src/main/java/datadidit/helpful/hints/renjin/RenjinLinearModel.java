package datadidit.helpful.hints.renjin;

import java.util.List;

import javax.script.ScriptException;

import datadidit.helpful.hints.renjin.model.RModel;
import datadidit.helpful.hints.renjin.model.RPrediction;

public interface RenjinLinearModel {
	RModel createModel(List<Double> x, List<Double> y) throws ScriptException;
	
	RModel getModel();
	
	RPrediction getLMResult(List<Double> x) throws ScriptException;
}
