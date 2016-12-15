/**
 * 
 */
package spms.controllers;

import java.util.Map;

import org.springframework.stereotype.Component;

import spms.abstracts.AbstractMemberDataBindingController;
import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
@Component("/member/delete.do")
public class MemberDeleteController extends AbstractMemberDataBindingController implements IController, IDataBinding {
	
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
