/**
 * 
 */
package net.foundation.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import net.foundation.abstracts.IMessageDAO;
import net.foundation.domain.MessageVO;

/**
 * @author HarryPaek
 *
 */
@Repository
public class MySqlMessageDAO implements IMessageDAO {
	private static final Logger logger = LoggerFactory.getLogger(MySqlMessageDAO.class);

	@Inject
	private SqlSession session;

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IMessageDAO#create(net.foundation.domain.MessageVO)
	 */
	@Override
	public void create(MessageVO vo) throws Exception {
        logger.info("create() ................");
		
		session.insert(getMapper("create"), vo);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IMessageDAO#read(int)
	 */
	@Override
	public MessageVO read(int mid) throws Exception {
        logger.info("read() ................");
		
		return session.selectOne(getMapper("read"), mid);
	}

	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IMessageDAO#updateState(int)
	 */
	@Override
	public void updateState(int mid) throws Exception {
        logger.info("updateState() ................");
		
		session.update(getMapper("updateState"), mid);
	}
	
	private String getMapper(String mapping)
	{
		String namesapce = "net.foundation.mapper.MessageDAO";
				
		return String.format("%s.%s", namesapce, mapping);
	}

}
