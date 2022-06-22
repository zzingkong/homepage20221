package egovframework.let.board.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;

import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.let.utl.fcc.service.FileMngUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class BoardController {
	
	@Resource(name = "boardService")
    private BoardService boardService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	@Resource(name = "fileMngUtil")
	private FileMngUtil fileUtil;
	
	//게시물 목록 가져오기
	@RequestMapping(value = "/board/selectList.do")
	public String selectList(@ModelAttribute("searchVO") BoardVO searchVO,  
			HttpServletRequest request, ModelMap model) throws Exception{
		//공지 게시글
		searchVO.setNoticeAt("Y");
		List<EgovMap> noticeResultList = boardService.selectBoardList(searchVO);
		model.addAttribute("noticeResultList", noticeResultList);
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); // 현재 페이지 번호
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit()); // 한 페이지당 게시되는 게시물 건 수
		paginationInfo.setPageSize(searchVO.getPageSize()); // 페이지 리스트에 게시되는 페이지 건수
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		searchVO.setNoticeAt("N");
		List<EgovMap> resultList = boardService.selectBoardList(searchVO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = boardService.selectBoardListCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt); // 전체 게시물 건 수
		model.addAttribute("paginationInfo", paginationInfo);
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_INFO", user);
		
		return "board/BoardSelectList";
	}
	
	//게시물 등록/수정
	@RequestMapping(value = "/board/boardRegist.do")
	public String boardRegist(@ModelAttribute("searchVO") BoardVO BoardVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null) {
			model.addAttribute("message", "로그인 후 사용가능합니다."); // 로그인을 해야지 이용할 수 있다.
			return "forward:/board/selectList.do";
		}else {
			model.addAttribute("USER_INFO", user);
		}
		
		BoardVO result = new BoardVO();
		if(!EgovStringUtil.isEmpty(BoardVO.getBoardId())) {
			result = boardService.selectBoard(BoardVO);
			//본인 및 관리자만 허용
			if(!user.getId().equals(result.getFrstRegisterId()) && !"admin".equals(user.getId())){
				model.addAttribute("message", "작성자 본인만 확인 가능합니다.");
				return "forward:/board/selectList.do";
			}
		}
		model.addAttribute("result", result);
		
		request.getSession().removeAttribute("sessionBoard"); //등록을 했을때 해당 세션을 삭제해야 다시 등록이 가능하기 때문에 쓴다
		
		return "board/BoardRegist";
	}
	
	//게시물 등록하기
	@RequestMapping(value = "/board/insert.do")
	//첨부파일에서는 final을 붙여줘야 보안면에서 안전하다
	public String insert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		//이중 서브밋 방지 체크
		if(request.getSession().getAttribute("sessionBoard") != null) {
			return "forward:/board/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null){
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/board/selectList.do";
		}
		
		List<FileVO> result = null;
		String atchFileId = "";
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "BOARD_", 0, "", "board.fileStorePath");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		searchVO.setAtchFileId(atchFileId);
		
		searchVO.setCreatIp(request.getRemoteAddr());
		searchVO.setUserId(user.getId());
		
		boardService.insertBoard(searchVO);
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionBoard", searchVO);
		return "forward:/board/selectList.do";
	}
	
	//게시물 가져오기
	@RequestMapping(value = "/board/select.do")
	public String seleect(@ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("USER_INFO", user);
		
		BoardVO result = boardService.selectBoard(searchVO);
		//비밀 글 여부 체크
		if("Y".equals(result.getOthbcAt())) {
			//본인 및 관리자만 허용
			if(user == null || user.getId() == null || (!user.getId().equals(result.getFrstRegisterId()) && !"admin".equals(user.getId()))) {
				model.addAttribute("message", "작성자 본인만 확인 가능합니다.");
				return "forward:/board/selectList.do";
			}
		}
		model.addAttribute("result", result);
		return "board/BoardSelect";
	}
	
	//게시물 수정하기
	@RequestMapping(value = "/board/update.do")
	public String update(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지
		if(request.getSession().getAttribute("sessionBoard") != null) {
			return "forward:/board/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null){
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/board/selectList.do";
		}else if("admin".equals(user.getId())){
			searchVO.setMngAt("Y");
		}
		String atchFileId = searchVO.getAtchFileId();
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(!files.isEmpty()) {
			if(EgovStringUtil.isEmpty(atchFileId)) {
				List<FileVO> result = fileUtil.parseFileInf(files, "BOARD_", 0, "", "board.fileStorePath");
				atchFileId = fileMngService.insertFileInfs(result);
				searchVO.setAtchFileId(atchFileId);
			}else {
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO>_result = fileUtil.parseFileInf(files, "BOARD_", cnt, atchFileId, "board.fileStorePath");
				fileMngService.updateFileInfs(_result);
			}
			}
		
		searchVO.setUserId(user.getId());
		
		boardService.updateBoard(searchVO);
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionBoard", searchVO);
		return "forward:/board/selectList.do";
	}
	
	//게시물 삭제하기
	@RequestMapping(value = "/board/delete.do")
	public String delete(@ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null){
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/board/selectList.do";
		}else if("admin".equals(user.getId())){
			searchVO.setMngAt("Y");
		}
		
		searchVO.setUserId(user.getId());
		
		boardService.deleteBoard(searchVO);
		
		return "forward:/board/selectList.do";
	}
	
	//메인페이지 
	@RequestMapping(value = "/board/index.do")
		public String main() throws Exception {
		return "board/BoardIndex";		
	}
	
}