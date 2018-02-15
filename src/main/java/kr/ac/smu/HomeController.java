package kr.ac.smu;

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

import kr.ac.smu.member.JdbcRegistDao;
import kr.ac.smu.member.MemberRegistRequest;
import kr.ac.smu.member.RegistDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController implements BeanFactoryAware{
	private RegistDao registDao;
	
	
	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		// TODO Auto-generated method stub
		registDao = context.getBean("jdbcRegistDao", JdbcRegistDao.class);
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
				List<MemberRegistRequest> list = registDao.select(req.getParameter("email"));
				System.out.println(list.get(0).getName());
				session.setAttribute("name", list.get(0).getName());
				out.println("<script>alert('로그인을 성공하였습니다!'); location.href='/smu/main'</script>");
				out.flush();
				out.close();
			}
			else {
				out.println("<script>alert('로그인 정보를 확인하세요!'); location.href='/smu/signup'</script>");
				out.flush();
				out.close();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/logout")
	public void logOut(HttpServletResponse res, HttpSession session) throws IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		session.removeAttribute("name");
		out.println("<script>alert('로그아웃을 성공하였습니다!'); location.href='/smu/main'</script>");
		out.flush();
		out.close();
		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp() {
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUpFinish(HttpServletRequest req){
		MemberRegistRequest mem = new MemberRegistRequest();
		try {
			req.setCharacterEncoding("UTF-8");	//POST방식 encoding 해결
			System.out.println(req.getParameter("name"));
			mem.setName(req.getParameter("name"));
			mem.setEmail(req.getParameter("email"));
			mem.setPassword(req.getParameter("password"));
			
			registDao.insert(mem);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return "redirect:/main";
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
	@RequestMapping(value = "/stat", method = RequestMethod.GET)
	public String showStat() {
		return "stat";
	}
	
//	private void databaseTest() {
//		System.out.println("database testing");
//		MemberRegistRequest msg = new MemberRegistRequest();
//		msg.setName("han");
//		msg.setEmail("dltkdals21010@naver.com");
//		msg.setPassword("asdfghj");
//		registDao.insert(msg);
//		
//		System.out.println("select test");
//		List<MemberRegistRequest> msgs = registDao.select(0, 2);
//		MemberRegistRequest msg1 = msgs.get(0);
//		System.out.println(msg1.getName());
//		
//	}
	
}
