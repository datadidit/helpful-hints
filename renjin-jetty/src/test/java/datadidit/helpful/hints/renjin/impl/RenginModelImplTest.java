package datadidit.helpful.hints.renjin.impl;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.junit.BeforeClass;
import org.junit.Test;

public class RenginModelImplTest {
	private static RenjinLinearModelImpl impl;
	
	@BeforeClass
	public static void setup(){
		impl = new RenjinLinearModelImpl();
	}
	
	@Test
	public void testRModel() throws ScriptException{
		//Test data 
		List<Double> x = new ArrayList<>();
		x.add(2.0);
		x.add(4.0);
		x.add(6.0);
		x.add(8.0);
		x.add(10.0);
		
		List<Double> y = new ArrayList<>();
		y.add(1.0);
		y.add(2.0);
		y.add(3.0);
		y.add(4.0);
		y.add(5.0);
		
		System.out.println(impl.createModel(x, y));
		
		x = new ArrayList<>(); 
		x.add(200.0);
		System.out.println(impl.getLMResult(x));
	}
}
