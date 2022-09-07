package egovframework.let.login.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.let.api.naver.service.NaverLoginService;
import egovframework.let.join.service.JoinService;
import egovframework.let.join.service.JoinVO;
import egovframework.let.uat.uia.service.EgovLoginService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;



@Controller
public class LoginController {
	@Resource(name = "loginService")
	private EgovLoginService loginService;
	
	
	@Resource(name ="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "naverLoginService")
	private NaverLoginService naverLoginService;
	
	@Resource
	private JoinService joinService;
	
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
	
	//네이버 로그인 콜백
	@RequestMapping(value="/login/naverLogin.do")
	public String naverLogin(@ModelAttribute("loginVO") LoginVO loginVO, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model)
	throws Exception {
		
		String domain = request.getServerName();
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginService.getAccessToken(session, code, state, domain);
		
		//로그인 사용자 정보를 읽어온다
		String apiResult = naverLoginService.getUserProfile(oauthToken);
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		JSONObject result=(JSONObject) jsonObj.get("response");
		
		loginVO.setId("NAVER-" + result.get("id").toString());	
		loginVO.setPassword("");
		loginVO.setUserSe("USR");
		
		LoginVO resultVO = loginService.actionLogin(loginVO);
		//로그인 값이 없으면 회원가입 처리
		if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("")) {
			request.getSession().setAttribute("LoginVO", resultVO);
			return "forward:/index.do";			
		}else {
			//일반 가입을 제외하고는 ID 값은 SNS명 + id 값
			JoinVO joinVO = new JoinVO();
			joinVO.setEmplyrId(loginVO.getId());
			joinVO.setUserNm(result.get("name").toString());
			joinVO.setPassword("");
			joinVO.setPasswordHint("SNS가입자");
			joinVO.setPasswordCnsr("SNS가입자");
			
			joinService.insertJoin(joinVO);
			model.addAttribute("loginMessage", "회원가입이 완료되었습니다.");
			
			return "forward:/index.do";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}