/**
 * 
 */
package spms.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import spms.abstracts.AbstractAuthController;
import spms.abstracts.IController;

/**
 * @author HarryPaek
 *
 */
@Component("/auth/logout.do")
public class LogoutController extends AbstractAuthController implements IController {

	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		HttpSession session = (HttpSession) model.get("session");
		session.invalidate();
		
		return "redirect:login.do";
	}
}
