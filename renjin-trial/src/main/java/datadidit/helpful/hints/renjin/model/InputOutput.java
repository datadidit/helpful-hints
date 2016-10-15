package datadidit.helpful.hints.renjin.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="InputOutput")
public class InputOutput {
	//TODO: Make more flexible by making this a map 
	private Double input; 
	
	private Double output;
	
	public InputOutput(){
		
	}

	public Double getInput() {
		return input;
	}

	public void setInput(Double input) {
		this.input = input;
	}

	public Double getOutput() {
		return output;
	}

	public void setOutput(Double output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "InputOutput [input=" + input + ", output=" + output + "]";
	}
	
	
}
