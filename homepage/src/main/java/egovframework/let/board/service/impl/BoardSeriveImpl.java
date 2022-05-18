package egovframework.let.board.service.impl;

import java.util.List;

import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

//어노테이션을 무조건 하나만 존재해야함
@Service("boardService")
public class BoardSeriveImpl extends EgovAbstractServiceImpl 
implements BoardService {
	
	@Resource(name = "boardMapper")
	private BoardMapper boardMapper;
	
	@Resource(name = "egovBoardIdGnrService")
	private EgovIdGnrService idgenService;
	
	//게시물 목록 가져오기
	public List<EgovMap> selectBoardList(BoardVO vo) throws Exception {
		return boardMapper.selectBoardList(vo);
	}
	
	//게시물 목록 수
	public int selectBoardListCnt(BoardVO vo) throws Exception {
		return boardMapper.selectBoardListCnt(vo);
 
   }
}