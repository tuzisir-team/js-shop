package common.db.model;

import java.util.HashMap;
import java.util.Iterator;

public class CommonModel {
	public HashMap condition = new HashMap();	
	public String IteratorModel(HashMap conditionTest, String moddile)
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
	
	public String IteratorFields(HashMap conditionTest)
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
	
	public String IteratorFieldsValues(HashMap conditionTest)
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
	
	public String getCondition() {
		return this.IteratorModel(this.condition, "and");
	}
	
	public String getFields() {
		return this.IteratorFields(this.condition);
	}
	
	public String getData() {
		return this.IteratorModel(this.condition, ",");
	}
}