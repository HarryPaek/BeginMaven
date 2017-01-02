package net.foundation.abstracts;

import java.util.List;

import net.foundation.domain.BoardVO;

public interface IBoardService {
	public void register(BoardVO vo) throws Exception;
	public BoardVO read(int bno) throws Exception;
	public void modify(BoardVO vo) throws Exception;
	public void remove(int bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;

}
