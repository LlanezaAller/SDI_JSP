package uo.sdi.acciones.util;

public class Asserts {
	
	public static boolean assertCampos(Object... o){
		for (int i=0 ; i < o.length ; i++) {
			if(o[i] == null)
				return false;
	    }
		return true;
	}
}
