package egovframework.let.crud2.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface Crud2Service {
	//임시데이터 가져오기
	public Crud2VO selectCrud2(Crud2VO vo) throws Exception;
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectCrud2List(Crud2VO vo) throws Exception;
	
	//임시데이터 등록하기
	public String insertCrud2(Crud2VO vo) throws Exception;
	
	//임시데이터 수정하기 
	public void updateCrud2(Crud2VO vo) throws Exception;
	
	//임시데이터 삭제하기
	public void deleteCrud2(Crud2VO vo) throws Exception;
	
	//임시데이터 목록 수
	public int selectCrud2ListCnt(Crud2VO vo) throws Exception;
}
   