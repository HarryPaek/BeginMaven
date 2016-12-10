/**
 * 
 */
package spms.controllers;

import java.util.HashMap;
import java.util.Map;

import spms.abstracts.AbstractMemberDataBindingController;
import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.annotation.Component;

/**
 * @author HarryPaek
 *
 */
@Component("/member/list.do")
public class MemberListController extends AbstractMemberDataBindingController implements IController, IDataBinding {

	@Override
	public Map<String, Class<?>> getDataBinders() {
		HashMap<String, Class<?>> binders = new HashMap<String, Class<?>>();
		binders.put("orderCond", String.class);
		
		return binders;
	}

	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderCond", model.get("orderCond"));

		model.put("members", dao.selectList(paramMap));
		
		return "MemberList.jsp";
	}
}
