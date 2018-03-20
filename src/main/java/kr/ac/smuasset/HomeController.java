package kr.ac.smuasset;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.ac.smuasset.dao.RegistDao;
import kr.ac.smuasset.dao.RegistDaoImpl;
import kr.ac.smuasset.dto.RegistDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController implements BeanFactoryAware{
	private RegistDao registDao;
	
	
	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		// TODO Auto-generated method stub
		
		registDao = context.getBean("registDaoImpl", RegistDaoImpl.class);
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	
	@RequestMapping(value ="/login", method = RequestMethod.POST)
	public void login(HttpServletResponse res, HttpServletRequest req, HttpSession session) throws IOException {
		try {
			res.setContentType("text/html; charset=UTF-8");
			req.setCharacterEncoding("UTF-8");	//POST방식 encoding 해결
			PrintWriter out = res.getWriter();
			if(registDao.loginCheck(req.getParameter("email"), req.getParameter("password"))) {
				List<RegistDto> list = registDao.select(req.getParameter("email"));
				session.setAttribute("name", list.get(0).getName());
				out.println("<script>alert('로그인을 성공하였습니다!'); location.href='/smuasset/main'</script>");
				out.flush();
				out.close();
			}
			else {
				out.println("<script>alert('로그인 정보를 확인하세요!'); location.href='/smuasset/main'</script>");
				out.flush();
				out.close();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="cwrite", method = RequestMethod.GET)
	public String communityWrite() {
		return "cwrite";
	}
	@RequestMapping(value="cwrite", method = RequestMethod.POST)
	public String communityWriteFinish() {
		return "index";
	}
	
	
	@RequestMapping(value="pwrite", method = RequestMethod.GET)
	public String pConsultWrite() {
		return "pwrite";
	}
	@RequestMapping(value="pwrite", method = RequestMethod.POST)
	public String pConsultWriteFinish() {
		return "index";
	}
	
	
	@RequestMapping(value="/logout")
	public void logOut(HttpServletResponse res, HttpSession session) throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		session.removeAttribute("name");
		out.println("<script>alert('로그아웃을 성공하였습니다!'); location.href='/smuasset/main'</script>");
		out.flush();
		out.close();
	}
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp() {
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public void signUpFinish(HttpServletRequest req, HttpServletResponse res) throws IOException{
		RegistDto registDto = new RegistDto();
		try {
			req.setCharacterEncoding("UTF-8");	//POST방식 encoding 해결
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			registDto.setName(req.getParameter("name"));
			registDto.setEmail(req.getParameter("email"));
			registDto.setPassword(req.getParameter("password"));
			
			registDao.insert(registDto);
			out.println("<script>alert('회원가입을 성공하였습니다!'); location.href='/smuasset/main'</script>");
			out.flush();
			out.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/gconsult", method = RequestMethod.GET)
	public String showGConsult() {
		return "gconsult";
	}
	
	@RequestMapping(value = "/pconsult", method = RequestMethod.GET)
	public String showPConsult() {
		return "pconsult";
	}
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String showNews() {
		return "news";
	}
	@RequestMapping(value = "/policy", method = RequestMethod.GET)
	public String showPolicy() {
		return "policy";
	}
	@RequestMapping(value = "/community", method = RequestMethod.GET)
	public String showCommunity() {
		return "community";
	}
}
