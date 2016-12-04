/**
 * 
 */
package spms.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author HarryPaek
 *
 */
public class ApplicationContext {
	HashMap<String, Object> objectTable = new HashMap<String, Object>();

	public ApplicationContext(String propertiesPath) throws Exception {
		Properties properties = new Properties();
		properties.load(new FileReader(propertiesPath));
		
		prepareObjects(properties);
		injectDependency();
	}
	
	public Object getBeans(String key) {
		return objectTable.get(key);
	}
	
	private void prepareObjects(Properties properties) throws Exception {
		Context ctx = new InitialContext();
		
		for (String key : properties.stringPropertyNames()) {
			if(key.startsWith("jndi."))
				objectTable.put(key, ctx.lookup(properties.getProperty(key)));
			else
				objectTable.put(key, Class.forName(properties.getProperty(key)).newInstance());
		}
	}
	
	private void injectDependency() throws Exception {
		for (String key : objectTable.keySet()) {
			if(!key.startsWith("jndi."))
				callSetter(objectTable.get(key));
		}
	}

	private void callSetter(Object object) throws Exception {
		for (Method method : object.getClass().getMethods()) {
			if(method.getName().startsWith("set")) {
				Object dependency = findObjectByType(method.getParameterTypes()[0]);
				
				if(dependency != null)
					method.invoke(object, dependency);
			}
		}
		
	}

	private Object findObjectByType(Class<?> type) {
		for (Object object : objectTable.values()) {
			if(type.isInstance(object))
				return object;
		}

		return null;
	}
}
