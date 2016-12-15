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
public abstract class AbstractMemberController implements IController {
    protected IMemberDao dao;
	
	@Autowired
    public IController setMemberDao(IMemberDao memberDao) {
		this.dao = memberDao;
		return this;
	}

	public abstract String execute(Map<String, Object> model) throws Exception;
}
