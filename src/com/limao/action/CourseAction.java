package com.limao.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.limao.dao.SkCourseDAO;
import com.limao.dao.SkUserDAO;
import com.limao.domain.SkCourse;
import com.limao.domain.SkUser;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@Controller
public class CourseAction extends BaseAction implements ModelDriven<SkCourse> {

	private SkCourse skCourse;
	@Autowired
	private SkUserDAO skUserDAO;
	@Autowired
	private SkCourseDAO skCourseDAO;

	@Override
	public SkCourse getModel() {
		return skCourse;
	}

	public void add() {
		skCourseDAO.save(skCourse);
	}

	public void del() {
		skCourseDAO.delete(skCourse);
	}

	public void modify() {
		skCourseDAO.merge(skCourse);
	}

	@SuppressWarnings("unchecked")
	public String findAll() {

		List<SkCourse> cls = skCourseDAO.findAll();
		getRequest().put("cls", cls);
		return "findAllSuccess";
	}

//	// 修改密码
//	public String changePwd() {
//		try {
//			SkUser skCourse1 = skCourseDAO.findByUserName(skCourse.getUserName())
//					.get(0);
//			skCourse.setUserId(skCourse1.getUserId());
//			skCourseDAO.merge(skCourse);
//
//			addFieldError("change_suc", getText("changePWD_suc"));
//		} catch (Exception e) {
//			addFieldError("change_err", getText("changePWD_err"));
//		}
//		return "result";
//	}

	// 登录
	@Override
	public String execute() throws Exception {
//		if (skCourseDAO.findUserExist(skCourse.getUserName(),
//				skCourse.getUserPassword())) {
//			getSession().put("user", skCourse);
//			return SUCCESS;
//		} else {
//			addFieldError("message_login_error", getText("login.error"));
//			return ERROR;
//		}
		return "";
	}

}
