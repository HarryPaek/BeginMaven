/**
 * 
 */
package net.foundation.domain;

import java.util.Date;

/**
 * @author HarryPaek
 *
 */
public class MessageVO {
	private int mid;
	private String targetId;
	private String sender;
	private String message;
	private Date opendate;
	private Date senddate;
	
	/**
	 * @return the mid
	 */
	public int getMid() {
		return mid;
	}
	/**
	 * @param mid the mid to set
	 */
	public void setMid(int mid) {
		this.mid = mid;
	}
	
	/**
	 * @return the targetId
	 */
	public String getTargetId() {
		return targetId;
	}
	/**
	 * @param targetId the targetId to set
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the opendate
	 */
	public Date getOpendate() {
		return opendate;
	}
	/**
	 * @param opendate the opendate to set
	 */
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	
	/**
	 * @return the senddate
	 */
	public Date getSenddate() {
		return senddate;
	}
	/**
	 * @param senddate the senddate to set
	 */
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("MessageVO [mid=%3d, targetId=%s, sender=%s]", mid, targetId, sender);
	}

}
