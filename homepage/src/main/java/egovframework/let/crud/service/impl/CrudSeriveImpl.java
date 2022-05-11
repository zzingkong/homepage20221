package egovframework.let.crud.service.impl;

import java.util.List;

import egovframework.let.crud.service.CrudService;
import egovframework.let.crud.service.CrudVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("crudService")
public class CrudSeriveImpl extends EgovAbstractServiceImpl 
implements CrudService {
	
	@Resource(name = "crudMapper")
	private CrudMapper crudMapper;
	
	@Resource(name = "egovCrudIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public CrudVO selectCrud(CrudVO vo) throws Exception {
		return crudMapper.selectCrud(vo);
	}
	
	//임시데이터 목록 가져오기
	@Override
	public List<EgovMap> selectCrudList(CrudVO vo) throws Exception {
	  return crudMapper.selectCrudList(vo); }
	
	//임시데이터 등록하기
	@Override
	public String insertCrud(CrudVO vo) throws Exception {
		String id = idgenService.getNextStringId();
		vo.setCrudId(id);
		crudMapper.insertCrud(vo);
		
		return id;		
	}
	
	//임시데이터 수정하기
	@Override
	public void updateCrud(CrudVO vo) throws Exception {
		crudMapper.updateCrud(vo);
		}
	//임시데이터 삭제하기
	@Override
	public void deleteCrud(CrudVO vo) throws Exception {
		crudMapper.deleteCrud(vo);
	}
	//임시데이터 목록 수
	@Override
	public int selectCrudListCnt(CrudVO vo) throws Exception {
		return crudMapper.selectCrudListCnt(vo);
	}
	
	
//	@Override
//	public TempVO selectTemp(TempVO vo) throws Exception {
//		return tempDAO.selectTemp(vo);
//   
}
