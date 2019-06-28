package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.BoardDTO;
import poly.dto.PagingDTO;

@Mapper("BoardMapper")
public interface BoardMapper {

	public int insertBoard(BoardDTO bDTO) throws Exception;

	public BoardDTO getBoardNo() throws Exception;

	public List<BoardDTO> getBoardList(PagingDTO pDTO) throws Exception;

	public BoardDTO getBoardDetail(BoardDTO bDTO) throws Exception;

	public int BoardRevise2(BoardDTO bDTO) throws Exception;

	public int BoardDelete(BoardDTO bDTO) throws Exception;

	public int updateViewCnt(BoardDTO bDTO) throws Exception;

	public int getBoardListTotalCount(String keyword) throws Exception;

	

	

}