/**
 * 
 */
package spms.abstracts;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author HarryPaek
 *
 */
public abstract class AbstractProjectController implements IController {
    protected IProjectDao dao;
	
	@Autowired
    public IController setMemberDao(IProjectDao projectDao) {
		this.dao = projectDao;
		return this;
	}

	public abstract String execute(Map<String, Object> model) throws Exception;
}
