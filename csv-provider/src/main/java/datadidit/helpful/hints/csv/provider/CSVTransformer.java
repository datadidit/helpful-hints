package datadidit.helpful.hints.csv.provider;

import java.util.Map;

public interface CSVTransformer {
	/**
	 * Utility method to Flatten POJO so that it can be converted into a CSV
	 * @return
	 */
	Map<?,?> flatten();
}
