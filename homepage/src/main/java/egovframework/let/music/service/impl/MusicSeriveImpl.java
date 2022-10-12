package egovframework.let.music.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.music.service.MusicService;
import egovframework.let.music.service.MusicVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

//어노테이션을 무조건 하나만 존재해야함
@Service("musicService")
public class MusicSeriveImpl // extends EgovAbstractServiceImpl 
implements MusicService {
	
	@Resource(name = "musicMapper")
	private MusicMapper musicMapper;
	
//	@Resource(name = "egovMusicIdGnrService")
//	private EgovIdGnrService idgenService;
	
	//게시물 목록 가져오기
	public List<EgovMap> selectMusicList(MusicVO vo) throws Exception {
		return musicMapper.selectMusicList(vo);
	}
	
	//임시데이터 목록 수
	public int selectMusicListCnt(MusicVO vo) throws Exception {
		return musicMapper.selectMusicListCnt(vo);
 	}	
	
	
	
//	//게시물 등록
//	@Override
//	public String insertMusic(MusicVO vo) throws Exception { 
//		String id = idgenService.getNextStringId();
//		vo.setMusicId(id);
//		musicMapper.insertMusic(vo);
//		
//		return id;
//   }
//	//게시물 상세정보
//	@Override
//	public MusicVO selectMusic(MusicVO vo) throws Exception {
//		//조회수 업
//		musicMapper.updateViewCnt(vo);
//				
//		return musicMapper.selectMusic(vo);
//	}
//	//게시물 수정하기
//	@Override
//	public void updateMusic(MusicVO vo) throws Exception{
//		musicMapper.updateMusic(vo);
//	}
//	
//	//게시물 삭제하기 
//	@Override
//	public void deleteMusic(MusicVO vo) throws Exception{
//		musicMapper.deleteMusic(vo);
//	}
	
	
	
	
	
	
}