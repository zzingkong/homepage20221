package egovframework.let.music.service.impl;

import java.util.List;

import egovframework.let.music.service.MusicVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Mapper("musicMapper")
public interface MusicMapper {
	
	   //게시물 목록 가져오기
		List<EgovMap> selectMusicList(MusicVO vo) throws Exception;
		
	   //게시물 목록 수 
		int selectMusicListCnt(MusicVO vo) throws Exception;
//		
//	   //게시물 등록 수 
//		void insertMusic(MusicVO vo) throws Exception;
//	
//	  //게시물 상세정보		
//		MusicVO selectMusic(MusicVO vo) throws Exception;
//		
//	  //조회수 업
//		void updateViewCnt(MusicVO vo) throws Exception;
//		
//	 //게시물 수정하기
//		void updateMusic(MusicVO vo) throws Exception;
//		
//	//게시물 삭제하기
//		void deleteMusic(MusicVO vo) throws Exception;
//
		}

