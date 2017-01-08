/**
 * 
 */
package net.foundation.abstracts;

import net.foundation.domain.MessageVO;

/**
 * @author HarryPaek
 *
 */
public interface IMessageDAO {
	public void create(MessageVO vo) throws Exception;
	
	public MessageVO read(int mid) throws Exception;
	
	public void updateState(int mid) throws Exception;
}
