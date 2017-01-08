/**
 * 
 */
package net.foundation.abstracts;

import net.foundation.domain.MessageVO;

/**
 * @author HarryPaek
 *
 */
public interface IMessageService {
	public void addMessage(MessageVO vo) throws Exception;
	
	public MessageVO readMessage(String uid, int mid) throws Exception;
}
