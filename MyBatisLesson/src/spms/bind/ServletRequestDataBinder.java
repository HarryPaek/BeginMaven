/**
 * 
 */
package spms.bind;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HarryPaek
 *
 */
public class ServletRequestDataBinder {

	public static Object bind(HttpServletRequest request, Class<?> dataType, String dataName) throws Exception {
		
		if(isPrimitiveType(dataType))
			return createValueObject(dataType, request.getParameter(dataName));
		
		Object dataObject = dataType.newInstance();
		
		for (String paramName : request.getParameterMap().keySet()) {
			Method method = findSetter(dataType, paramName);
			
			if(method != null)
				method.invoke(dataObject, createValueObject(method.getParameterTypes()[0], request.getParameter(paramName)));
		}
		
		return dataObject;
	}

	private static boolean isPrimitiveType(Class<?> type) {
		if(type.getName().equals("int") || type == Integer.class ||
		   type.getName().equals("long") || type == Long.class ||
		   type.getName().equals("float") || type == Float.class ||
		   type.getName().equals("double") || type == Double.class ||
		   type.getName().equals("boolean") || type == Boolean.class ||
		   type == Date.class || type == String.class) {
			
			return true;
		}
		
		return false;
	}
	
	private static Object createValueObject(Class<?> type, String value) {
		if(type.getName().equals("int") || type == Integer.class)
			return new Integer(value);
		else if(type.getName().equals("long") || type == Long.class)
			return new Long(value);
		else if(type.getName().equals("float") || type == Float.class)
			return new Float(value);
		else if(type.getName().equals("double") || type == Double.class)
			return new Double(value);
		else if(type.getName().equals("boolean") || type == Boolean.class)
			return new Double(value);
		else if(type == Date.class)
			return java.sql.Date.valueOf(value);
		else
			return value;
	}
	
	private static Method findSetter(Class<?> type, String name) {
		Method[] methods = type.getMethods();
		
		for (Method method : methods) {
			String methodName = method.getName();
			if(!methodName.startsWith("set")) continue;
			
			if(methodName.substring(3).equalsIgnoreCase(name))
				return method;
		}

		return null;
	}
}
