package egovframework.let.join.web;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.let.join.service.JoinService;
import egovframework.let.join.service.JoinVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import net.sf.json.JSONObject;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class JoinController {

	@Resource(name = "joinService")
	private JoinService joinService;
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource engovMessageSource;
	
	//회원구분
	@RequestMapping(value = "/join/memberType.do")
	public String memberType(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		return "join/MemberType";
	}
	
	//회원등록 폼
	@RequestMapping(value= "/join/memberRegist.do") 
	public String memberRegist(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		return "join/MemberRegist";
	}
	
	//회원가입
	@RequestMapping(value = "/join/insertMember.do")
	public String insertMember(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		if(!EgovStringUtil.isEmpty(vo.getLoginType())) {
			//일반기업을 제외하고는 ID값은 SNS명+ ID값
			if(!("normal").equals(vo.getLoginType())) {
				vo.setEmplyrId(vo.getLoginType() + "-" + vo.getEmplyrId());
				vo.setPassword("");
				vo.setPasswordHint("SNS가입자");
				vo.setPasswordCnsr("SNS가입자");
			}
		}
		// ajax에서 사용했는데 왜 이 로직이 한번더 들어갈까? 중복하는 것을 막기 위해서
		if(joinService.duplicateCheck(vo) > 0) {
			model.addAttribute("message", engovMessageSource.getMessage("fail.duplicate.member"));
			//이미 사용중인 ID입니다.
			return "forward:/join/memberType.do";
		}else {
			joinService.insertJoin(vo);
			model.addAttribute("message", engovMessageSource.getMessage("join.request.msg"));
			//회원신청이 정상적을 완료되었습니다. 로그인 후 이용해 주세요.
		}
		return "forward:/index.do";
	
	}
	
	//아이디 중복체크
	@RequestMapping(value="/join/duplicateCheck.do")
	public void duplicateCheck(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String successYn = "Y";
		String message = "성공";	
	
		JSONObject jo = new JSONObject();	
		response.setContentType("application/json; charset=utf-8");  /*보안때문에 많이 사용*/
		
		int duplicateCnt = joinService.duplicateCheck(vo);
		if(duplicateCnt > 0) {
			successYn = "N";
			message = engovMessageSource.getMessage("fail.duplicate.member");// 이미 사용중인 id 입니다
		}
			
		
		jo.put("successYn", successYn);
		jo.put("message", message);
		
		PrintWriter printwriter = response.getWriter();
		printwriter.println(jo.toString());
		printwriter.flush();
		printwriter.close();
	}
}