package datadidit.helpful.hints.renjin;

import java.io.FileNotFoundException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.renjin.primitives.matrix.Matrix;
import org.renjin.script.RenjinScriptEngine;
import org.renjin.sexp.AttributeMap;
import org.renjin.sexp.DoubleArrayVector;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.ListVector;
import org.renjin.sexp.SEXP;
import org.renjin.sexp.Vector;

public class RenjinTest {
	private static ScriptEngine engine;
	
	@BeforeClass
	public static void setup(){
    	//TODO: Make sure you compile with 1.8
    	// create a script engine manager:
        ScriptEngineManager manager = new ScriptEngineManager();
        
        // create a Renjin engine:
        engine = manager.getEngineByName("Renjin");
        RenjinScriptEngine rEngine = (RenjinScriptEngine) engine;
        System.out.println(rEngine.getFactory().getEngineName());
        // check if the engine has loaded correctly:
        if(engine == null) {
            throw new RuntimeException("Renjin Script Engine not found on the classpath.");
        }	
	}
	
	@Test
	public void test() throws ScriptException{
        engine.eval("df <- data.frame(x=1:10, y=(1:10)+rnorm(n=10))");
        engine.eval("print(df)");
        engine.eval("print(lm(y ~ x, df))");
	}
	
	@Test
	public void testScript() throws FileNotFoundException, ScriptException{
		engine.eval(new java.io.FileReader("src/test/resources/script.R"));
	}
	
	@Test
	public void testRintoJavaP1() throws ScriptException{
		// evaluate Renjin code from String:
		SEXP res = (SEXP)engine.eval("a <- 2; b <- 3; a*b");

		// print the result to stdout:
		System.out.println("The result of a*b is: " + res);
		// determine the Java class of the result:
		Class objectType = res.getClass();
		System.out.println("Java class of 'res' is: " + objectType.getName());
		// use the getTypeName() method of the SEXP object to get R's type name:
		System.out.println("In R, typeof(res) would give '" + res.getTypeName() + "'");
		
		//*************************************
		// More examples
		//*************************************
		DoubleVector dvRes = (DoubleVector)engine.eval("a <- 2; b <- 3; a*b");

		Vector vRes = (Vector)engine.eval("a <- 2; b <- 3; a*b");
		
		Vector x = (Vector)engine.eval("x <- c(6, 7, 8, 9)");
		System.out.println("The vector 'x' has length " + x.length());
		for (int i = 0; i < x.length(); i++) {
		    System.out.println("Element x[" + (i + 1) + "] is " + x.getElementAsDouble(i));
		}
		
		ListVector lx =
			    (ListVector)engine.eval("x <- list(name = \"Jane\", age = 23, scores = c(6, 7, 8, 9))");
			System.out.println("List 'x' has length " + lx.length());
			// directly access the first (and only) element of the vector 'x$name':
			System.out.println("x$name is '" + lx.getElementAsString(0) + "'");
			
			
		DoubleVector scores = (DoubleVector)x.getElementAsSEXP(2);
		
		//Matrix Example
		Vector mRes = (Vector)engine.eval("matrix(seq(9), nrow = 3)");
		if (mRes.hasAttributes()) {
		    AttributeMap attributes = mRes.getAttributes();
		    Vector dim = attributes.getDim();
		    if (dim == null) {
		        System.out.println("Result is a vector of length " +
		            mRes.length());

		    } else {
		        if (dim.length() == 2) {
		            System.out.println("Result is a " +
		                dim.getElementAsInt(0) + "x" +
		                dim.getElementAsInt(1) + " matrix.");
		        } else {
		            System.out.println("Result is an array with " +
		                dim.length() + " dimensions.");
		        }
		    }
		}
		
		mRes = (Vector)engine.eval("matrix(seq(9), nrow = 3)");
		try {
		    Matrix m = new Matrix(mRes);
		    System.out.println("Result is a " + m.getNumRows() + "x"
		        + m.getNumCols() + " matrix.");
		} catch(IllegalArgumentException e) {
		    System.out.println("Result is not a matrix: " + e);
		}
		
		//***************************************************
		// Model Example
		//***************************************************
		ListVector model = (ListVector)engine.eval("x <- 1:10; y <- x*3; lm(y ~ x)");
		Vector coefficients = model.getElementAsVector("coefficients");
		// same result, but less convenient:
		// int i = model.indexOfName("coefficients");
		// Vector coefficients = (Vector)model.getElementAsSEXP(i);

		System.out.println("intercept = " + coefficients.getElementAsDouble(0));
		System.out.println("slope = " + coefficients.getElementAsDouble(1));
	}
	
	//*****************************************
	// Push data from Java to R 
	//*****************************************
	@Test
	public void testPushJavaToR() throws ScriptException{
		engine.put("x", 4);
		engine.put("y", new double[] { 1d, 2d, 3d, 4d });
		engine.put("z", new DoubleArrayVector(1,2,3,4,5));
		engine.put("hashMap", new java.util.HashMap());
		// some R magic to print all objects and their class with a for-loop:
		engine.eval("for (obj in ls()) { " +
		    "cmd <- parse(text = paste('typeof(', obj, ')', sep = ''));" +
		    "cat('type of ', obj, ' is ', eval(cmd), '\\n', sep = '') }");
	}
}
