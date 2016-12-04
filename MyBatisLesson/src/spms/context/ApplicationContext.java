/**
 * 
 */
package spms.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

import spms.annotation.Component;

/**
 * @author HarryPaek
 *
 */
public class ApplicationContext {
	HashMap<String, Object> objectTable = new HashMap<String, Object>();

	public Object getBeans(String key) {
		return objectTable.get(key);
	}
	
	public void addBean(String name, Object object)
	{
		objectTable.put(name, object);
	}
	
	public void prepareObjectsByProperties(String propertiesPath) throws Exception {
		Properties properties = new Properties();
		properties.load(new FileReader(propertiesPath));

		Context ctx = new InitialContext();
		
		for (String key : properties.stringPropertyNames()) {
			if(key.startsWith("jndi."))
				objectTable.put(key, ctx.lookup(properties.getProperty(key)));
			else
				objectTable.put(key, Class.forName(properties.getProperty(key)).newInstance());
		}
	}
	
	public void prepareObjectsByAnnotation(String basePackage) throws Exception {
		Reflections reflector = new Reflections(basePackage);
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		
		for (Class<?> clazz : list) {
			String key = clazz.getAnnotation(Component.class).value();
			objectTable.put(key, clazz.newInstance());
		}
	}
	
	public void injectDependency() throws Exception {
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
