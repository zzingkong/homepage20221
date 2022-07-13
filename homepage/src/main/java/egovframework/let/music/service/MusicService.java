package egovframework.let.music.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface MusicService {
	
	//게시물 목록 가져오기
	public List<EgovMap> selectMusicList(MusicVO vo) throws Exception;
		
	//게시물 목록 수
	public int selectMusicListCnt(MusicVO vo) throws Exception;
//	
//	//게시물 등록하기
//	public String insertMusic(MusicVO vo) throws Exception;
//	
//	//게시물 상세정보
//	public MusicVO selectMusic(MusicVO vo) throws Exception;
//	
//	//게시물 수정하기
//	public void updateMusic(MusicVO vo) throws Exception;
//	
//	//게시물 삭제하기
//	public void deleteMusic(MusicVO vo) throws Exception;
}
   