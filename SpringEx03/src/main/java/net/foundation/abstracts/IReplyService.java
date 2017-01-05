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
public interface IReplyService {
	public void addReply(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> listReply(int bno)  throws Exception;

	public void modifyReply(ReplyVO vo) throws Exception;
	
	public void removeReply(int rno) throws Exception;
	
    public List<ReplyVO> listReplyPage(int bno, Criteria  criteria) throws Exception;
	
	public int getTotalCount(int bno) throws Exception;
}
