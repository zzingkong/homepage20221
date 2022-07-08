package egovframework.let.music;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.music.service.MusicService;
import egovframework.let.music.service.MusicVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class MusicController {
	//
	@Autowired
	private MusicService musicService;   
	
	@RequestMapping(path="/music/musicList.do")  //이 주소로 요청이 오면 상세페이지 실행
	public String name(MusicVO vo, Map map) throws Exception {
		//이 주소로 요청이 오면 안에 작성된 명령어 실행
		System.out.println("야호"+vo.getMusicTitle());
		//vo객체로 condition정보를 줘야함 디비에서 조회해서 나온게 list에 담겨나옴
		List<EgovMap> list = musicService.selectMusicList(vo);
		map.put("MusicList", list);
		return "music/musicList";
	}

}
