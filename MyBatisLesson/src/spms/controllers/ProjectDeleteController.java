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
@Component("/project/delete.do")
public class ProjectDeleteController extends AbstractProjectDataBindingController implements IController, IDataBinding {

	/* (non-Javadoc)
	 * @see spms.abstracts.AbstractProjectController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
        Project project = (Project) model.get("project");

        dao.delete(project.getNo());
			
		return "ProjectDeleteSuccess.jsp";
	}

}
