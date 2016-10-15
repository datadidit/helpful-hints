package datadidit.helpful.hints.renjin.rest.bean;

import java.util.List;

public class LMInputBean {
	@Override
	public String toString() {
		return "LMInputBean [x=" + x + ", y=" + y + "]";
	}

	private List<Double> x; 
	
	private List<Double> y; 
	
	public LMInputBean(){
		
	}

	public List<Double> getX() {
		return x;
	}

	public void setX(List<Double> x) {
		this.x = x;
	}

	public List<Double> getY() {
		return y;
	}

	public void setY(List<Double> y) {
		this.y = y;
	}
	
	
}
