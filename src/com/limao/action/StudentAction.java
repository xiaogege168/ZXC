package com.limao.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limao.dao.SkCourseDAO;
import com.limao.dao.SkShopDAO;
import com.limao.dao.SkStudentDAO;
import com.limao.domain.SkStudent;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
@Service
public class StudentAction extends BaseAction implements ModelDriven<SkStudent> {
	@Autowired
	private SkStudent skStudent;
	@Autowired
	private SkStudentDAO skStudentDAO;
	
	@Autowired
	private SkCourseDAO skCourseDAO;
	@Autowired
	private SkShopDAO skShopDAO;

	@Override
	public SkStudent getModel() {
		return skStudent;
//		org.apache.velocity.runtime.defaults.velocity.properties
	}

	public String add() {
	int a=	Integer.parseInt(ServletActionContext.getRequest().getParameter("skCourseId"));
	int b=	Integer.parseInt(ServletActionContext.getRequest().getParameter("skShopId"));
		skStudent.setSkCourse(skCourseDAO.findById(a));
		skStudent.setSkShop(skShopDAO.findById(b));
		skStudent.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		skStudentDAO.save(skStudent);
		findAll();
		return "findAll";
	}

	public String del() {
		skStudentDAO.delete(skStudent);
		return "findAll";
	}
	
	public String toadd() {
		getRequest().put("createTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "toadd";
	}

	public String modify() {
		skStudentDAO.merge(skStudent);
		return "findAll";
	}

	@SuppressWarnings("unchecked")
	public String findByMonth() {
		List<SkStudent> lss= skStudentDAO.findByMonth(0);
		getRequest().put("stus",lss);
		return "findAll";
	}
	
	
	@SuppressWarnings("unchecked")
	public String findAll() {
		List<SkStudent> lss= skStudentDAO.findAll();
		getRequest().put("stus",lss);
		System.out.println( skStudentDAO.findAll().size()+"================");
		return "findAll";
	}

	// 修改密码
	public String changePwd() {
		try {
			SkStudent skStudent1 = skStudentDAO.findByStuName(
					skStudent.getStuName()).get(0);
			skStudent.setStuId(skStudent1.getStuId());
			skStudentDAO.merge(skStudent);

			addFieldError("change_suc", getText("changePWD_suc"));
		} catch (Exception e) {
			addFieldError("change_err", getText("changePWD_err"));
		}
		return "result";
	}

	// 登录
	@Override
	public String execute() throws Exception {
		if (skStudentDAO.findUserExist(skStudent.getStuName(),
				skStudent.getStuPassword())) {
			SkStudent	skStudent1=(SkStudent) skStudentDAO.findByStuName(skStudent.getStuName()).get(0);
			getSession1().setAttribute("user", skStudent1);
			return SUCCESS;
		} else {
			addFieldError("message_login_error", "姓名/密码 输入错误！请重新输入");
			return INPUT;
		}

	}

}
