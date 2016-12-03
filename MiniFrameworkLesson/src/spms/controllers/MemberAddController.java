/**
 * 
 */
package spms.controllers;

import java.util.HashMap;
import java.util.Map;

import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.abstracts.IMemberDao;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public class MemberAddController implements IController, IDataBinding {
    IMemberDao dao;
	
	public IController setMemberDao(IMemberDao memberDao) {
		this.dao = memberDao;
		return this;
	}
	
	@Override
	public Map<String, Class<?>> getDataBinders() {
		HashMap<String, Class<?>> binders = new HashMap<String, Class<?>>();
		binders.put("member", spms.vo.Member.class);
		
		return binders;
	}
	
	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member");

		if(member.getEmail() == null)      //if GET Method
			return "MemberForm.jsp";
		else {                                //else POST Method
			dao.insert(member);
			
			return "MemberAddSuccess.jsp";
		}
	}
}
