/**
 * 
 */
package net.foundation.domain;

import java.util.Date;

/**
 * @author HarryPaek
 *
 */
public class ReplyVO {
	private int rno;
	private int bno;
	private String replyText;
	private String replyer;
	
	private Date regdate;
	private Date updatedate;
	
	/**
	 * @return the rno
	 */
	public int getRno() {
		return rno;
	}
	/**
	 * @param rno the rno to set
	 */
	public void setRno(int rno) {
		this.rno = rno;
	}
	
	/**
	 * @return the bno
	 */
	public int getBno() {
		return bno;
	}

	/**
	 * @return the replyText
	 */
	public String getReplyText() {
		return replyText;
	}
	/**
	 * @param replyText the replyText to set
	 */
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	/**
	 * @param bno the bno to set
	 */
	public void setBno(int bno) {
		this.bno = bno;
	}
	

	/**
	 * @return the replyer
	 */
	public String getReplyer() {
		return replyer;
	}
	/**
	 * @param replyer the replyer to set
	 */
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	
	/**
	 * @return the regdate
	 */
	public Date getRegdate() {
		return regdate;
	}
	/**
	 * @param regdate the regdate to set
	 */
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	/**
	 * @return the updatedate
	 */
	public Date getUpdatedate() {
		return updatedate;
	}
	/**
	 * @param updatedate the updatedate to set
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("ReplyVO [rno=%3d, bno=%8d, replyer=%s]", rno, bno, replyer);
	}

}
