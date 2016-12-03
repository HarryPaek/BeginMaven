/**
 * 
 */
package spms.controllers;

import java.util.Map;

import spms.abstracts.IController;
import spms.abstracts.IMemberDao;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public class MemberDeleteController implements IController {
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
		Member member = (Member) model.get("member");

		dao.delete(member.getNo());
			
		return "MemberDeleteSuccess.jsp";
	}
}
