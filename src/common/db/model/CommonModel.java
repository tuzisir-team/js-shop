package common.db.model;

import java.util.HashMap;
import java.util.Iterator;

public class CommonModel {

	public static String IteratorModel(HashMap conditionTest, String moddile)
	{
		String sqlTest = "";
		Iterator<String> iter = conditionTest.keySet().iterator();
		int num = 0;
		while(iter.hasNext()){
			   String key=iter.next();
			if (num == 0)
			{
				sqlTest += key + "=" + conditionTest.get(key);
			} else {
				sqlTest += " " + moddile + " " + key + "=" + conditionTest.get(key);
			}  
			num ++;
		}
		return sqlTest;
	}
	
	public static String IteratorFields(HashMap conditionTest)
	{
		String sqlTest = "";
		Iterator<String> iter = conditionTest.keySet().iterator();
		int num = 0;
		while(iter.hasNext()){
			   String key=iter.next();
			if (num == 0)
			{
				sqlTest += key;
			} else {
				sqlTest += " , "+key;
			}  
			num ++;
		}
		return sqlTest;
	}
	
	public static String IteratorFieldsValues(HashMap conditionTest)
	{
		String sqlTest = "";
		Iterator<String> iter = conditionTest.keySet().iterator();
		int num = 0;
		while(iter.hasNext()){
			String key=iter.next();
			if (num == 0)
			{
				sqlTest += conditionTest.get(key);
			} else {
				sqlTest += " , "+conditionTest.get(key);
			}  
			num ++;
		}
		return sqlTest;
	}
}
