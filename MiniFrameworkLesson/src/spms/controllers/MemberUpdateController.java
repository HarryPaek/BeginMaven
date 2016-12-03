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
public class MemberUpdateController implements IController {

	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		MemberDao dao = (MemberDao) model.get("memberDao");
		Member member = (Member) model.get("member");
		
		if(member.getEmail() == null) {      //if GET Method
			//model의 Member 데이터를 DB에서 조회한 데이터로 교체
			model.put("member", dao.select(member.getNo()));
			
			return "MemberUpdateForm.jsp";
		}
		else {                                //else POST Method
			dao.update(member);
			
			return "redirect:list.do";
		}
	}

}
