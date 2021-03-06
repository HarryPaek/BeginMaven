/**
 * 
 */
package spms.controllers;

import java.util.HashMap;
import java.util.Map;

import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.abstracts.IMemberDao;
import spms.annotation.Component;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
@Component("/member/delete.do")
public class MemberDeleteController implements IController, IDataBinding {
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

		dao.delete(member.getNo());
			
		return "MemberDeleteSuccess.jsp";
	}
}
