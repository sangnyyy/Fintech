package kr.ac.smu;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
//	@RequestMapping(value ="/login", method = RequestMethod.POST)
//	public String login(HttpServletRequest req) {
//		MemberRegistRequest mem = new MemberRegistRequest();
//		try {
//			req.setCharacterEncoding("UTF-8");	//POST방식 encoding 해결
//			mem = registDao.select(req.getParameter("email"), req.getParameter("password")).get(0);
//			System.out.println("email :" +mem.getEmail() +" password : " +mem.getPassword());
//			if(mem.getEmail() != null && mem.getPassword() != null) return "redirect:/main";
//			else {
//				return "redirect:/signup";
//			}
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "redirect:/signup";
//		}
//	}
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
//	@RequestMapping(value="/show")
//	public String show(HttpServletRequest req, Model model) {
//		
//		int start = 0;
//		int size = registDao.count();
//		List<MemberRegistRequest> mems = registDao.select(start, size);
//		model.addAttribute("mems", mems);
//		return "show";
//		
//	}
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
