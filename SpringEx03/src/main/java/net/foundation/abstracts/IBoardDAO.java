/**
 * 
 */
package net.foundation.abstracts;

import java.util.List;

import net.foundation.domain.BoardVO;
import net.foundation.domain.Criteria;
import net.foundation.domain.SearchCriteria;

/**
 * @author HarryPaek
 *
 */
public interface IBoardDAO {
	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(int bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(int bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(int page) throws Exception;
	
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception;
	
	public int getTotalCount() throws Exception;
	
	public List<BoardVO> listSearch(SearchCriteria criteria) throws Exception;
	
	public int getTotalCountSearch(SearchCriteria criteria) throws Exception;
}
