/**
 * 
 */
package spms.controllers;

import java.util.Map;

import spms.abstracts.AbstractMemberDataBindingController;
import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.annotation.Component;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
@Component("/member/add.do")
public class MemberAddController extends AbstractMemberDataBindingController implements IController, IDataBinding {
	
	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member) model.get("member");

		if(member.getEmail() == null)      //if GET Method
			return "MemberForm.jsp";
		else {                                //else POST Method
			dao.insert(member);
			
			return "MemberAddSuccess.jsp";
		}
	}
}
