package egovframework.let.crud2.service.impl;

import java.util.List;

import egovframework.let.crud2.service.Crud2VO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Mapper("crud2Mapper")
public interface Crud2Mapper {
	
	//임시데이터 가져오기
	Crud2VO selectCrud2(Crud2VO vo) throws Exception;

	//임시데이터 목록 가져오기
	List<EgovMap> selectCrud2List(Crud2VO vo) throws Exception;
	
	//임시데이터 등록
	void insertCrud2(Crud2VO vo) throws Exception;
	
	//임시데이터 수정하기
	void updateCrud2(Crud2VO vo) throws Exception;
	
	//임시데이터 삭제하기
	void deleteCrud2(Crud2VO vo) throws Exception;
	
	//임시데이터 목록 수
	int selectCrud2ListCnt(Crud2VO vo) throws Exception;

}
