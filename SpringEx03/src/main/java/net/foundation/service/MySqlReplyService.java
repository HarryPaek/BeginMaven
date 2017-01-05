/**
 * 
 */
package net.foundation.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.foundation.abstracts.IReplyDAO;
import net.foundation.abstracts.IReplyService;
import net.foundation.domain.Criteria;
import net.foundation.domain.ReplyVO;

/**
 * @author HarryPaek
 *
 */
@Service
public class MySqlReplyService implements IReplyService {
	private static final Logger logger = LoggerFactory.getLogger(MySqlReplyService.class);
	
	@Inject
	private IReplyDAO dao;

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IReplyService#addReply(net.foundation.domain.ReplyVO)
	 */
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		logger.info("addReply() ................");
		
		dao.create(vo);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IReplyService#listReply(int)
	 */
	@Override
	public List<ReplyVO> listReply(int bno) throws Exception {
		logger.info("listReply() ................");
		
		return dao.list(bno);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IReplyService#modifyReply(net.foundation.domain.ReplyVO)
	 */
	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		logger.info("modifyReply() ................");
		
		dao.update(vo);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IReplyService#removeReply(int)
	 */
	@Override
	public void removeReply(int rno) throws Exception {
		logger.info("removeReply() ................");
		
		dao.delete(rno);;
	}

	@Override
	public List<ReplyVO> listReplyPage(int bno, Criteria criteria) throws Exception {
		logger.info("listReplyPage() ................");
		
		return dao.listPage(bno, criteria);
	}

	@Override
	public int getTotalCount(int bno) throws Exception {
		logger.info("getTotalCount() ................");
		
		return dao.count(bno);
	}

}
