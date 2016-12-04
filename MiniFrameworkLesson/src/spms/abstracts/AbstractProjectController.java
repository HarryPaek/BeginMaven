/**
 * 
 */
package spms.abstracts;

import java.util.Map;

/**
 * @author HarryPaek
 *
 */
public abstract class AbstractProjectController implements IController {
    protected IProjectDao dao;
	
	public IController setMemberDao(IProjectDao projectDao) {
		this.dao = projectDao;
		return this;
	}

	public abstract String execute(Map<String, Object> model) throws Exception;
}
