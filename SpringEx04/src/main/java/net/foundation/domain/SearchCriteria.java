/**
 * 
 */
package net.foundation.domain;

/**
 * @author HarryPaek
 *
 */
public class SearchCriteria extends Criteria {
	private String searchType;
	private String keyword;
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s SearchCriteria [searchType=%s, keyword=%s]", super.toString(), searchType, keyword);
	}

}
