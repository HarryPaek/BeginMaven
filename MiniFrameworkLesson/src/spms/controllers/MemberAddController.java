/**
 * 
 */
package spms.controllers;

import java.util.Map;

import spms.abstracts.IController;
import spms.abstracts.IMemberDao;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public class MemberAddController implements IController {
    IMemberDao dao;
	
	public IController setMemberDao(IMemberDao memberDao) {
		this.dao = memberDao;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(!model.containsKey("member"))      //if GET Method
			return "MemberForm.jsp";
		else {                                //else POST Method
			Member member = (Member) model.get("member");
			
			dao.insert(member);
			
			return "MemberAddSuccess.jsp";
		}
	}
}
