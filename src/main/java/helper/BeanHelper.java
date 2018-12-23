package helper;

public class BeanHelper {
	
	public BeanHelper() {};
	
	public String resolveNull(String string) {
		if(string == null) {
			string = "";
		}
		return string;
	}

}
