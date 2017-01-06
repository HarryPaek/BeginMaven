/**
 * 
 */
package net.foundation.abstracts;

import java.util.List;

import net.foundation.domain.Criteria;
import net.foundation.domain.ReplyVO;

/**
 * @author HarryPaek
 *
 */
public interface IReplyDAO {
	public List<ReplyVO> list(int bno) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(int rno) throws Exception;
	
	public List<ReplyVO> listPage(int bno, Criteria  criteria) throws Exception;
	
	public int count(int bno) throws Exception;
	
	public int getBno(int rno) throws Exception;
}
