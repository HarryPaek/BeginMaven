/**
 * 
 */
package net.foundation.domain;

/**
 * @author HarryPaek
 *
 */
public class Criteria {
	private int page;
	private int perPageCount;
	
	public Criteria() {
		this.page = 1;
		this.perPageCount = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
			
		this.page = page;
	}

	public int getPerPageCount() {
		return perPageCount;
	}

	public void setPerPageCount(int perPageCount) {
		if(perPageCount <= 0 || perPageCount > 100) {
			this.perPageCount = 10;
			return;
		}
		
		this.perPageCount = perPageCount;
	}
	
	public int getPageStart() {
		return (this.page - 1) * this.perPageCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Criteria [page=%d, perPageCount=%d]", page, perPageCount);
	}
}
