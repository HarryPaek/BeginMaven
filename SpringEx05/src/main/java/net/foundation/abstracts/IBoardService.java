package net.foundation.abstracts;

import java.util.List;

import net.foundation.domain.BoardVO;
import net.foundation.domain.Criteria;
import net.foundation.domain.SearchCriteria;

public interface IBoardService {
	public void register(BoardVO vo) throws Exception;
	public BoardVO read(int bno) throws Exception;
	public void modify(BoardVO vo) throws Exception;
	public void remove(int bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception;
	public int getTotalCount() throws Exception;
	
    public List<BoardVO> listSearchCriteria(SearchCriteria criteria) throws Exception;
	public int getTotalCount(SearchCriteria criteria) throws Exception;
	
	public List<String> getAttach(int bno) throws Exception;
}
