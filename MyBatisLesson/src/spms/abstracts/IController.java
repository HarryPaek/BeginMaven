/**
 * 
 */
package spms.abstracts;

import java.util.Map;

/**
 * @author HarryPaek
 *
 */
public interface IController {
	String execute(Map<String, Object> model) throws Exception;
}
