package datadidit.helpful.hints.renjin.model;

import org.renjin.sexp.ListVector;
import org.renjin.sexp.Vector;

public class RModel {
	private Double intercept; 
	
	private Double slope;
	
	private Double rSquared; 
	
	private Double adjustedRSquared;
	
	public RModel(){}
	
	public RModel(ListVector model){
		Vector coefficients = model.getElementAsVector("coefficients");
		System.out.println("Names");
		System.out.println(model.getElementAsVector("residuals"));
		intercept = coefficients.getElementAsDouble(0);
		slope = coefficients.getElementAsDouble(1);
	}

	public Double getIntercept() {
		return intercept;
	}

	public void setIntercept(Double intercept) {
		this.intercept = intercept;
	}

	public Double getSlope() {
		return slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	public Double getrSquared() {
		return rSquared;
	}

	public void setrSquared(Double rSquared) {
		this.rSquared = rSquared;
	}

	public Double getAdjustedRSquared() {
		return adjustedRSquared;
	}

	public void setAdjustedRSquared(Double adjustedRSquared) {
		this.adjustedRSquared = adjustedRSquared;
	}

	@Override
	public String toString() {
		return "RModel [intercept=" + intercept + ", slope=" + slope + ", rSquared=" + rSquared + ", adjustedRSquared="
				+ adjustedRSquared + "]";
	}
	
	
}
