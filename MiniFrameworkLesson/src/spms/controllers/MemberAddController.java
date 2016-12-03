/**
 * 
 */
package spms.controllers;

import java.util.Map;

import spms.abstracts.IController;
import spms.dao.MemberDao;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public class MemberAddController implements IController {

	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(!model.containsKey("member"))      //if GET Method
			return "MemberForm.jsp";
		else {                                //else POST Method
			MemberDao dao = (MemberDao) model.get("memberDao");
			Member member = (Member) model.get("member");
			
			dao.insert(member);
			
			return "MemberAddSuccess.jsp";
		}
	}
}
