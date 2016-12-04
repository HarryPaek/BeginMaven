/**
 * 
 */
package spms.controllers;

import java.util.Map;

import spms.abstracts.IController;
import spms.abstracts.IMemberDao;
import spms.annotation.Component;

/**
 * @author HarryPaek
 *
 */
@Component("/member/list.do")
public class MemberListController implements IController {
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
		model.put("members", dao.selectList());
		
		return "MemberList.jsp";
	}
}
