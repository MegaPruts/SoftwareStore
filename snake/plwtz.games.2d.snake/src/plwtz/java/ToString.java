package plwtz.java;

import java.lang.reflect.Field;

public class ToString {

	public static String toString(Object subject) {
		StringBuilder result = new StringBuilder();
		result.append(subject.getClass().getSimpleName());
		try {
			for (int i = 0; i < subject.getClass().getDeclaredFields().length; i++) {
				Field f = subject.getClass().getDeclaredFields()[i];
				f.setAccessible(true);
				result.append(" " + f.getName() + "(" + f.get(subject) + ")");
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}

}
