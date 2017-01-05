/**
 * 
 */
package net.foundation.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author HarryPaek
 *
 */
public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private Criteria criteria;
	private int displayPageCount = 10;

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}

	/**
	 * @return the startPage
	 */
	public int getStartPage() {
		return startPage;
	}
	
	/**
	 * @return the endPage
	 */
	public int getEndPage() {
		return endPage;
	}
	
	/**
	 * @return the prev
	 */
	public boolean isPrev() {
		return prev;
	}
	
	/**
	 * @return the next
	 */
	public boolean isNext() {
		return next;
	}

	/**
	 * @return the criteria
	 */
	public Criteria getCriteria() {
		return criteria;
	}
	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("PageMaker [totalCount=%d, startPage=%d, endPage=%d, prev=%b, next=%b]", totalCount, startPage, endPage, prev, next);
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageCount", criteria.getPerPageCount())
				.build();
		
		return uriComponents.toUriString();
	}
	
	private void calcData() {
		endPage = (int)(Math.ceil(criteria.getPage() / (double)displayPageCount) * displayPageCount);
		
		startPage = (endPage - displayPageCount) + 1;
		
		int tempEndPage = (int)(Math.ceil(totalCount / (double)criteria.getPerPageCount()));
		
		if(endPage > tempEndPage)
			endPage = tempEndPage;
		
		prev = startPage == 1 ? false : true;
		
		next = endPage * criteria.getPerPageCount() >= totalCount ? false : true;
	}
	
}
