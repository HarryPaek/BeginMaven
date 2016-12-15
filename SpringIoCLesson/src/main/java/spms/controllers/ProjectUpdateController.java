/**
 * 
 */
package spms.controllers;

import java.util.Map;

import org.springframework.stereotype.Component;

import spms.abstracts.AbstractProjectDataBindingController;
import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.vo.Project;

/**
 * @author HarryPaek
 *
 */
@Component("/project/update.do")
public class ProjectUpdateController extends AbstractProjectDataBindingController implements IController, IDataBinding {

	/* (non-Javadoc)
	 * @see spms.abstracts.AbstractProjectController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");
		
		if(project.getTitle() == null) {
			model.put("project", dao.select(project.getNo()));
			
			return "ProjectUpdateForm.jsp";
		}
		else {
			dao.update(project);
			
			return "redirect:list.do";
			//return String.format("redirect:update.do?no=%d", project.getNo());
		}
	}
}
