/**
 * 
 */
package spms.abstracts;

import java.util.List;
import javax.sql.DataSource;
import spms.vo.Member;

/**
 * @author HarryPaek
 *
 */
public interface IMemberDao {
	void setDataSource(DataSource ds);
	List<Member> selectList() throws Exception;
	int insert(Member member) throws Exception;
	Member select(int no) throws Exception;
	int update(Member member) throws Exception;
	int delete(int no) throws Exception;
	Member exist(String email, String password) throws Exception;
}
