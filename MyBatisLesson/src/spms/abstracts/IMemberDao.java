/**
 * 
 */
package spms.abstracts;

import java.util.HashMap;
import java.util.List;

import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public interface IMemberDao {
	List<Member> selectList(HashMap<String, Object> paramMap) throws Exception;
	int insert(Member member) throws Exception;
	Member select(int no) throws Exception;
	int update(Member member) throws Exception;
	int delete(int no) throws Exception;
	Member exist(String email, String password) throws Exception;
}
