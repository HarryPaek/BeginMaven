/**
 * 
 */
package net.foundation.abstracts;

import java.util.List;

import net.foundation.domain.BoardVO;

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
}
