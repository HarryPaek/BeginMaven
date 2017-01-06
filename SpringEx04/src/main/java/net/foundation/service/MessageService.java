/**
 * 
 */
package net.foundation.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.foundation.abstracts.IMessageDAO;
import net.foundation.abstracts.IMessageService;
import net.foundation.abstracts.IPointDAO;
import net.foundation.domain.MessageVO;

/**
 * @author HarryPaek
 *
 */
@Service
public class MessageService implements IMessageService {
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	@Inject
	IMessageDAO messageDao;
	
	@Inject
	IPointDAO   pointDao;

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IMessageService#addMessage(net.foundation.domain.MessageVO)
	 */
	@Override
	public void addMessage(MessageVO vo) throws Exception {
        logger.info("addMessage() ................");
        logger.info(vo.toString());
		
        messageDao.create(vo);
        pointDao.updatePoint(vo.getSender(), 10);
	}

	/*
	 * (non-Javadoc)
	 * @see net.foundation.abstracts.IMessageService#readMessage(java.lang.String, int)
	 */
	@Override
	public MessageVO readMessage(String uid, int mid) throws Exception {
        logger.info("readMessage() ................");
        
        messageDao.updateState(mid);
        pointDao.updatePoint(uid, 5);
        
        return messageDao.read(mid);
	}

}
