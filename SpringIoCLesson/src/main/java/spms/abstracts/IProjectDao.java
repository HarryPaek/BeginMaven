/**
 * 
 */
package spms.abstracts;

import java.util.HashMap;
import java.util.List;

import spms.vo.Project;

/**
 * @author HarryPaek
 *
 */
public interface IProjectDao {
	List<Project> selectList(HashMap<String, Object> paramMap) throws Exception;
	int insert(Project project) throws Exception;
	Project select(int no) throws Exception;
	int update(Project project) throws Exception;
	int delete(int no) throws Exception;
}
