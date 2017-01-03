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
public class SearchPageMaker extends PageMaker {
	private SearchCriteria searchCriteria;

	/**
	 * @return the searchCriteria
	 */
	public SearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	/**
	 * @param criteria the searchCriteria to set
	 */
	public void setSearchCriteria(SearchCriteria criteria) {
		super.setCriteria(criteria);
		this.searchCriteria = criteria;
	}

	/* (non-Javadoc)
	 * @see net.foundation.domain.PageMaker#makeQuery(int)
	 */
	@Override
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageCount", searchCriteria.getPerPageCount())
				.queryParam("searchType", searchCriteria.getSearchType())
				.queryParam("keyword", searchCriteria.getKeyword())
				.build();
		
		return uriComponents.toUriString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("SearchPageMaker [totalCount=%d, startPage=%d, endPage=%d, prev=%b, next=%b]", getTotalCount(), getStartPage(), getEndPage(), isPrev(), isNext());
	}

}
