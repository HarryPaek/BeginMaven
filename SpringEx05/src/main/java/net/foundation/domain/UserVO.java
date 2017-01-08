/**
 * 
 */
package net.foundation.domain;

/**
 * @author HarryPaek
 *
 */
public class UserVO {
	private String uid;
	private String upw;
	private String uname;
	private int upoint;

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the upw
	 */
	public String getUpw() {
		return upw;
	}
	/**
	 * @param upw the upw to set
	 */
	public void setUpw(String upw) {
		this.upw = upw;
	}

	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}
	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

	/**
	 * @return the upoint
	 */
	public int getUpoint() {
		return upoint;
	}
	/**
	 * @param upoint the upoint to set
	 */
	public void setUpoint(int upoint) {
		this.upoint = upoint;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("UserVO [uid=%s, upw=%s, upoint=%5d]", uid, upw, upoint);
	}
	
}
