/**
 * 
 */
package spms.controllers;

import java.util.Map;

import spms.abstracts.AbstractProjectController;
import spms.abstracts.IController;
import spms.annotation.Component;

/**
 * @author HarryPaek
 *
 */
@Component("/project/list.do")
public class ProjectListController extends AbstractProjectController implements IController {

	/* (non-Javadoc)
	 * @see spms.abstracts.IController#execute(java.util.Map)
	 */
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("projects", dao.selectList());

		return "ProjectList.jsp";
	}
}
