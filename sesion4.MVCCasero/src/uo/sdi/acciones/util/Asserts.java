package uo.sdi.acciones.util;

import java.util.regex.Pattern;

public class Asserts {
	
	public static boolean assertCampos(String... o){
		for (int i=0 ; i < o.length ; i++) {
			if(o[i] == null || o[i].isEmpty())
				return false;
	    }
		return true;
	}
	
	public static boolean isEmail(String e){
		String email_pattern = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(email_pattern);
		return pattern.matcher(e).matches();
	}
}
