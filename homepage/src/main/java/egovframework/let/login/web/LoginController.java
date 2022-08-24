package egovframework.let.login.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;

import egovframework.let.uat.uia.service.EgovLoginService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {
	@Resource(name = "loginService")
	private EgovLoginService loginService;
	
	
	@Resource(name ="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	//로그인
	@RequestMapping(value = "login/actionLogin.do")
	public String actionLogin(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, ModelMap model) throws Exception {
		
			LoginVO resultVO = loginService.actionLogin(loginVO);
			if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("")) {
				request.getSession().setAttribute("LoginVO", resultVO);
				return "redirect:/index.do";
			}else {
				model.addAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login"));
				return "redirect:/index.do";		
				}	
			}
	//로그아웃
	@RequestMapping(value= "login/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
		request.getSession().invalidate();
		
		//RequestContextHolder.getRequestAttributes().removeAttribute("LoginVO",RequestAtrributes.SCOPE_SESSION); 로그인 세션만 날려버릴거면! 
		return "forward:/index.do";  //전체 사용자 로그아웃 접속하는 순간 행위에 대한 모든 세션 다 날아감 
		
	}
}