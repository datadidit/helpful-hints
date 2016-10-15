package datadidit.helpful.hints.renjin.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.renjin.sexp.ListVector;
import org.renjin.sexp.Vector;

@XmlRootElement(name="Model")
public class RModel {
	private String formula; 

	private Double intercept; 
	
	private Double slope;
	
	private Double rSquared; 
	
	private Double adjustedRSquared;
	
	public RModel(){}
	
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

	public void setRSquared(Double rSquared) {
		this.rSquared = rSquared;
	}

	public Double getAdjustedRSquared() {
		return adjustedRSquared;
	}

	public void setAdjustedRSquared(Double adjustedRSquared) {
		this.adjustedRSquared = adjustedRSquared;
	}
	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	@Override
	public String toString() {
		return "RModel [formula=" + formula + ", intercept=" + intercept
				+ ", slope=" + slope + ", rSquared=" + rSquared
				+ ", adjustedRSquared=" + adjustedRSquared + "]";
	}
}
