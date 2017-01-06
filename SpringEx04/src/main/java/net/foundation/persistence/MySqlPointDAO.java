/**
 * 
 */
package net.foundation.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import net.foundation.abstracts.IPointDAO;

/**
 * @author HarryPaek
 *
 */
@Repository
public class MySqlPointDAO implements IPointDAO {
	private static final Logger logger = LoggerFactory.getLogger(MySqlPointDAO.class);

	@Inject
	private SqlSession session;


	/* (non-Javadoc)
	 * @see net.foundation.abstracts.IPointDAO#updatePoint(java.lang.String, int)
	 */
	@Override
	public void updatePoint(String uid, int point) throws Exception {
        logger.info("updatePoint() ................");
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        paramMap.put("point", point);
		
		session.update(getMapper("updatePoint"), paramMap);
	}

	private String getMapper(String mapping)
	{
		String namesapce = "net.foundation.mapper.PointDAO";
				
		return String.format("%s.%s", namesapce, mapping);
	}

}
