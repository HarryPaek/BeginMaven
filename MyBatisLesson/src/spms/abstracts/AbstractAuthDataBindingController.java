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
public abstract class AbstractAuthDataBindingController extends AbstractMemberController implements IDataBinding {

	/* (non-Javadoc)
	 * @see spms.abstracts.IDataBinding#getDataBinders()
	 */
	@Override
	public Map<String, Class<?>> getDataBinders() {
		HashMap<String, Class<?>> binders = new HashMap<String, Class<?>>();
		binders.put("loginInfo", spms.vo.Member.class);
		
		return binders;
	}
}
