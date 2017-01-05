/**
 * 
 */
package net.foundation.domain;

/**
 * @author HarryPaek
 *
 */
public class SampleVO {
	private int mno;
	private String firstName;
	private String lastName;
	
	/**
	 * @return the mno
	 */
	public int getMno() {
		return mno;
	}
	/**
	 * @param mno the mno to set
	 */
	public void setMno(int mno) {
		this.mno = mno;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("SampleVO [mno=%3d, firstName=%s, lastName=%s]", mno, firstName, lastName);
	}

}
