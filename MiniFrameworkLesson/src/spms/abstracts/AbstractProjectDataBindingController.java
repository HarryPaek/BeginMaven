/**
 * 
 */
package spms.abstracts;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HarryPaek
 *
 */
public abstract class AbstractProjectDataBindingController extends AbstractProjectController implements IDataBinding {

	/* (non-Javadoc)
	 * @see spms.abstracts.IDataBinding#getDataBinders()
	 */
	@Override
	public Map<String, Class<?>> getDataBinders() {
		HashMap<String, Class<?>> binders = new HashMap<String, Class<?>>();
		binders.put("project", spms.vo.Project.class);
		
		return binders;
	}
}
