package egovframework.let.music;

import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.music.service.MusicService;
import egovframework.let.music.service.MusicVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class MusicController {
	//
	@Autowired
	private MusicService musicService;   
	
	@RequestMapping(path="/music/musicList.do")  //이 주소로 요청이 오면 상세페이지 실행
	public String name(@ModelAttribute("vo") MusicVO vo, Map map, ModelMap model) throws Exception {
		//이 주소로 요청이 오면 안에 작성된 명령어 실행 	
		PaginationInfo paginationInfo = new PaginationInfo(); 
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
	
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		int totCnt = musicService.selectMusicListCnt(vo);
	
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		//vo객체로 condition정보를 줘야함 디비에서 조회해서 나온게 list에 담겨나옴
		List<EgovMap> list = musicService.selectMusicList(vo);
		map.put("MusicList", list);
		
		return "music/musicList";
		}
	
	@RequestMapping(path="/music/musicselectList.do") 
	public String list()throws Exception {
		return "music/musicselectlist";
	}	

	}

