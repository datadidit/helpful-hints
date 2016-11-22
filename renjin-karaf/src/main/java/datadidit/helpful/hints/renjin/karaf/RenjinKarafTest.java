package datadidit.helpful.hints.renjin.karaf;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.renjin.script.RenjinScriptEngine;
import org.renjin.script.RenjinScriptEngineFactory;

public class RenjinKarafTest 
{
	//private ScriptEngine engine;
	
	public RenjinKarafTest(){

	}
	
	public void testRuntime() throws ScriptException{
    	//TODO: Make sure you compile with 1.8
    	// create a script engine manager:
        //ScriptEngineManager manager = new ScriptEngineManager();
        
        // create a Renjin engine:
        //engine = manager.getEngineByName("Renjin");
		RenjinScriptEngineFactory factory = new RenjinScriptEngineFactory();
		RenjinScriptEngine engine = factory.getScriptEngine();
		
		// check if the engine has loaded correctly:
        if(engine == null) {
            throw new RuntimeException("Renjin Script Engine not found on the classpath.");
        }	
        
        engine.eval("df <- data.frame(x=1:10, y=(1:10)+rnorm(n=10))");
        engine.eval("print(df)");
        engine.eval("print(lm(y ~ x, df))");
	}
}
