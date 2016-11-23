package datadidit.helpful.hints.renjin.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Prediction")
public class RPrediction {
	private List<InputOutput> results = new ArrayList<>();

	public RPrediction(){}
	
	public List<InputOutput> getResults() {
		return results;
	}

	public void setResults(List<InputOutput> results) {
		this.results = results;
	}
	
	public void addResult(InputOutput result){
		results.add(result);
	}

	@Override
	public String toString() {
		return "RPrediction [results=" + results + "]";
	}
}
