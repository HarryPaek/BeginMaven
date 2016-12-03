/**
 * 
 */
package spms.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.abstracts.IController;
import spms.dao.MemberDao;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public class LoginController implements IController {

	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(!model.containsKey("member"))      //if GET Method
			return "LoginForm.jsp";
		else {                                //else POST Method
			MemberDao dao = (MemberDao) model.get("memberDao");
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
