package egovframework.let.crud2.web;
import java.util.List;

import egovframework.let.crud2.service.Crud2Service;
import egovframework.let.crud2.service.Crud2VO;
import egovframework.let.utl.fcc.service.EgovStringUtil;


import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class Crud2Controller {

	@Resource(name = "crud2Service")
	private Crud2Service crud2Service;
	
	//임시데이터 가져오기
	@RequestMapping(value = "/crud2/select.do")  //브라우저에서 치는 url
		public String select(@ModelAttribute("serchVO") Crud2VO serchVO,
		 HttpServletRequest request, ModelMap model) throws Exception {
		
		Crud2VO result = crud2Service.selectCrud2(serchVO);
		model.addAttribute("result", result);
		return "crud2/Crud2Select";
		}
	
	//임시데이터목록 가져오기
	@RequestMapping(value = "/crud2/selectList.do")
	public String selectList(@ModelAttribute("searchVO") Crud2VO searchVO, HttpServletRequest request, ModelMap model) 
	throws Exception { 

	PaginationInfo paginationInfo = new PaginationInfo();
	
	paginationInfo.setCurrentPageNo(searchVO.getPageIndex() );
	paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
	paginationInfo.setPageSize(searchVO.getPageSize() );
	
	searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex() );
	searchVO.setLastIndex(paginationInfo.getLastRecordIndex() );
	searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage() );
	
	int totCnt = crud2Service.selectCrud2ListCnt(searchVO);
	
	paginationInfo.setTotalRecordCount(totCnt);
	model.addAttribute("paginationInfo" , paginationInfo);
	
	List<EgovMap> resultList = crud2Service.selectCrud2List(searchVO);
	model.addAttribute("resultList", resultList);
			
	return "crud2/Crud2SelectList";
	}	
	
	//임시데이터 등록/수정
	@RequestMapping(value = "/crud2/crud2Regist.do")
	public String crud2Regist(@ModelAttribute("searchVO") Crud2VO crud2VO,
	HttpServletRequest request, ModelMap model) throws Exception { 
		
    Crud2VO result = new Crud2VO();
    if(!EgovStringUtil.isEmpty(crud2VO.getCrudId())) {
    	result = crud2Service.selectCrud2(crud2VO);    }
    model.addAttribute("result", result);
	
	return "crud2/Crud2Regist";  //jsp page 선언하는 곳
		
}
	//임시데이터 등록하기 
	@RequestMapping(value = "/crud2/insert.do")
	public String insert(@ModelAttribute("searchVO") Crud2VO searchVO,
		HttpServletRequest request, ModelMap model) throws Exception { 
		crud2Service.insertCrud2(searchVO);	
		return "forward:/crud2/selectList.do";
	}
	//임시데이터 수정하기
	@RequestMapping(value = "/crud2/update.do")
	public String update(@ModelAttribute("searchVO") Crud2VO searchVO,
		HttpServletRequest request, ModelMap model) throws Exception { 
		
		crud2Service.updateCrud2(searchVO);	
		return "forward:/crud2/selectList.do";
	}
	
	//임시데이터 삭제하기
	@RequestMapping(value = "/crud2/delete.do")
	public String delete(@ModelAttribute("searchVO") Crud2VO searchVO,
		HttpServletRequest request, ModelMap model) throws Exception { 		
		crud2Service.deleteCrud2(searchVO);	
		return "forward:/crud2/selectList.do";
	}		

		
}
