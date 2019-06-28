package poly.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.BoardDTO;
import poly.dto.PagingDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.BoardMapper;
import poly.service.HashKey;
import poly.service.IBoardService;
import poly.util.CmmUtil;

@Service("BoardService")
public class BoardService implements IBoardService {

	@Resource(name = "BoardMapper")
	private BoardMapper boardMapper;

	@Override
	public int insertBoard(BoardDTO bDTO) throws Exception {
		
		//게시판 번호 가져오기
		BoardDTO tDTO = boardMapper.getBoardNo();
		
		//게시판 번호를 못가져왔을때 에러 대비용으로 메모리 올리기
		if (tDTO==null) {
			tDTO = new BoardDTO();
		}
		
		//게시판번호를 boardNo 변수에 저장하기
		String boardNo = CmmUtil.nvl(tDTO.getBoardNo());
		
		//기존 전달받은 게시판정보(제목, 내용 등등등)에 앞서 저장한 게시판번호 넣기
		bDTO.setBoardNo(boardNo);
		
		return boardMapper.insertBoard(bDTO);
	}

	

	@Override
	public BoardDTO getBoardDetail(BoardDTO bDTO) throws Exception {
		
		return boardMapper.getBoardDetail(bDTO);
	}

	@Override
	public int BoardRevise2(BoardDTO bDTO) throws Exception {
		
		
		return boardMapper.BoardRevise2(bDTO);
	}

	@Override
	public int BoardDelete(BoardDTO bDTO) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.BoardDelete(bDTO);
	}

	@Override
	public int updateViewCnt(BoardDTO bDTO) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.updateViewCnt(bDTO);
	}

	@Override
	public int getBoardListTotalCount(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.getBoardListTotalCount(keyword);
	}



	@Override
	public List<BoardDTO> getBoardList(PagingDTO pDTO) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.getBoardList(pDTO);
	}

	

	

	

	
}