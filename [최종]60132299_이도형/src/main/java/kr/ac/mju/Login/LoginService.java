package kr.ac.mju.Login;

import java.sql.SQLException;

import kr.ac.mju.Contsants.Constants;
import kr.ac.mju.User.User;
import kr.ac.mju.User.UserDAO;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class LoginService {
	private String msg;
	private User user;

	public ModelAndView checkLogin(String id, String pwd) throws ClassNotFoundException, SQLException {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", getMsg());
		if(checkID(id)) {
			if(checkPwd(id,pwd)) {
				mav.addObject("msg", getMsg());
		        mav.setViewName("myPage");
				return mav;
			}
		}
		mav.addObject("msg", getMsg());
        mav.setViewName("home");
		return mav;
	}

	public boolean checkID(String id) throws ClassNotFoundException, SQLException {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.checkId(id);
		
		if(user != null) {
			return true;
		}
		setMsg(Constants.LoginMsg.ID.getMsg());
		return false;
	}
	
	public boolean checkPwd(String id, String pwd) throws ClassNotFoundException, SQLException  {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.checkIdPwd(id, pwd);
		
		if(user != null) {
			setUser(new User(user.getID(), user.getPwd(), user.getName(), user.getType()));
			setMsg(Constants.LoginMsg.SUCCESS.getMsg());
			return true;
		}
		
		setMsg(Constants.LoginMsg.PASSWORD.getMsg());
		return false;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
