/**
 * 
 */
package spms.controllers;

import java.util.Map;

import spms.abstracts.IController;
import spms.dao.MemberDao;

/**
 * @author HarryPaek
 *
 */
public class MemberListController implements IController {

	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		MemberDao dao = (MemberDao) model.get("memberDao");
		model.put("members", dao.selectList());
		
		return "MemberList.jsp";
	}
}
