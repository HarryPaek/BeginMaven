/**
 * 
 */
package spms.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.abstracts.IController;
import spms.abstracts.IMemberDao;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public class LoginController implements IController {
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
			return "LoginForm.jsp";
		else {                                //else POST Method
			Member member = (Member) model.get("member");
			
			Member loginUser = dao.exist(member.getEmail(), member.getPassword());
			
			if(loginUser != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", loginUser);
				
				return String.format("redirect:%s/member/list.do", session.getServletContext().getContextPath());
			}
			
           	return "LoginFail.jsp";
		}
	}
}
