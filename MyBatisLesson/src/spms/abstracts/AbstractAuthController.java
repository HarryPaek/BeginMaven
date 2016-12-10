/**
 * 
 */
package spms.abstracts;

import java.util.Map;

/**
 * @author HarryPaek
 *
 */
public abstract class AbstractAuthController implements IController {
    protected IMemberDao dao;
	
	public IController setMemberDao(IMemberDao memberDao) {
		this.dao = memberDao;
		return this;
	}

	public abstract String execute(Map<String, Object> model) throws Exception;
}
