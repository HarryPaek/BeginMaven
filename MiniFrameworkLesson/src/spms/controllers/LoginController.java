/**
 * 
 */
package spms.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.abstracts.IMemberDao;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public class LoginController implements IController, IDataBinding {
    IMemberDao dao;
	
	public IController setMemberDao(IMemberDao memberDao) {
		this.dao = memberDao;
		return this;
	}
	
	@Override
	public Map<String, Class<?>> getDataBinders() {
		HashMap<String, Class<?>> binders = new HashMap<String, Class<?>>();
		binders.put("loginInfo", spms.vo.Member.class);
		
		return binders;
	}
	
	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member loginInfo = (Member) model.get("loginInfo");

		if(loginInfo.getEmail() == null)   //if GET Method
			return "LoginForm.jsp";
		else {                                //else POST Method
			Member loginUser = dao.exist(loginInfo.getEmail(), loginInfo.getPassword());
			
			if(loginUser != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("member", loginUser);
				
				return String.format("redirect:%s/member/list.do", session.getServletContext().getContextPath());
			}
			
           	return "LoginFail.jsp";
		}
	}
}
