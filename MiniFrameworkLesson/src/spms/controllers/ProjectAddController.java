/**
 * 
 */
package spms.controllers;

import java.util.Map;

import spms.abstracts.AbstractProjectDataBindingController;
import spms.abstracts.IController;
import spms.abstracts.IDataBinding;
import spms.annotation.Component;
import spms.vo.Project;

/**
 * @author HarryPaek
 *
 */
@Component("/project/add.do")
public class ProjectAddController extends AbstractProjectDataBindingController implements IController, IDataBinding {

	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project) model.get("project");

		if(project.getTitle() == null  )      //if GET Method
			return "ProjectAddForm.jsp";
		else {                                //else POST Method
			dao.insert(project);
			
			return "ProjectAddSuccess.jsp";
		}
	}

}
