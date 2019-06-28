package poly.service;

import java.util.HashMap;
import java.util.List;

import poly.dto.BoardDTO;
import poly.dto.PagingDTO;

public interface IBoardService {

	public int insertBoard(BoardDTO bDTO) throws Exception;

	public List<BoardDTO> getBoardList(PagingDTO pDTO) throws Exception;

	public BoardDTO getBoardDetail(BoardDTO bDTO) throws Exception;

	public int BoardRevise2(BoardDTO bDTO) throws Exception;

	public int BoardDelete(BoardDTO bDTO) throws Exception;

	public int updateViewCnt(BoardDTO bDTO) throws Exception;

	public int getBoardListTotalCount(String keyword) throws Exception;

	

	

	

	

}