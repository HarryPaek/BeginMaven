/**
 * 
 */
package spms.controllers;

import java.util.HashMap;
import java.util.Map;

import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.abstracts.IMemberDao;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public class MemberUpdateController implements IController, IDataBinding {
    IMemberDao dao;
	
	public IController setMemberDao(IMemberDao memberDao) {
		this.dao = memberDao;
		return this;
	}
	
	@Override
	public Map<String, Class<?>> getDataBinders() {
		HashMap<String, Class<?>> binders = new HashMap<String, Class<?>>();
		binders.put("member", spms.vo.Member.class);
		
		return binders;
	}

	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
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
