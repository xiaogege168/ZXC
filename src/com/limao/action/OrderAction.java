package com.limao.action;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;

import org.apache.struts2.ServletActionContext;
import org.hibernate.metamodel.domain.Superclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.limao.dao.SkShopDAO;
import com.limao.dao.SkStuOrderDAO;
import com.limao.dao.SkStudentDAO;
import com.limao.domain.SkShop;
import com.limao.domain.SkStuOrder;
import com.limao.domain.SkStudent;
import com.limao.domain.SkUser;
import com.opensymphony.xwork2.ModelDriven;
//@Controller
public class OrderAction extends BaseAction implements ModelDriven<SkStuOrder> {
	@Autowired
	private SkStuOrder skStuOrder;
	@Autowired
	private SkStuOrderDAO skStuOrderDAO;
	@Autowired
	private SkStudentDAO skStudentDAO;
	private String info;
	private boolean isToday = false;

	@Override
	public SkStuOrder getModel() {
/*		System.out.println(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		System.out.println(new SimpleDateFormat("yyyyMMdd").format(skStuOrder
				.getOrderTime())+"#########");*/
		
		return skStuOrder;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isToday() {
		SkStudent skStudent=(SkStudent)getSession().get("user");
		int stuId=skStudent.getStuId();
		
		
		SkStuOrder s=skStuOrderDAO.findLast(stuId);
		if (s==null&&getSession().containsKey("user")) {
			isToday=true;
		}else if (!s.getOrderTime().equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
			if (getSession().containsKey("user")) {
				isToday=true;
			}
		}
		
		getRequest().put("ok", isToday);
		getRequest().put("okk", isToday ==true?false:true);
		return isToday;
	}

	public void setToday(boolean isToday) {
		this.isToday = isToday;
	}

	public String add() {
		skStuOrder.setOrderTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		SkStudent skStudent = (SkStudent) getSession1().getAttribute("user");
		
		SkStudent st=skStudentDAO.findById(skStudent.getStuId());
		String shopName=st.getSkShop().getAddress();
		
		
		getRequest().put("shopName", shopName);
		skStuOrder.setSkStudent(skStudent);
		skStuOrderDAO.save(skStuOrder);
		findByStu();
		return "success";
	}

	public String del() {
		int a=Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("orderId"));
		int b=(Integer)getRequest().get("orderId");
		skStuOrder=skStuOrderDAO.findById(a);
		skStuOrderDAO.delete(skStuOrder);
		findByStu();
		info = "删除预约成功！";
		return "success";
	}

	public String modify() {
		skStuOrderDAO.merge(skStuOrder);
		info = "修改预约成功！";
		return "success";
	}


	@SuppressWarnings("unchecked")
	public String findAll() {
		List<SkStuOrder> ols = skStuOrderDAO.findAll();
		getRequest().put("ols", ols);
		return "success";
	}
	
	public String findByShop() {
		
		int shopId=Integer.parseInt(ServletActionContext.getRequest().getParameter("shopId"));
		List<SkStuOrder> ols = skStuOrderDAO.findByShop(shopId);
		getRequest().put("ols", ols);
		return "success";
	}
	
	
	@SuppressWarnings("unchecked")
	public String findByStu() {
		SkStudent st = (SkStudent) getSession1().getAttribute("user");
		int shopId=st.getSkShop().getShopId(); 
		List<SkStuOrder> a=(List<SkStuOrder>) skStuOrderDAO.count(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "10:30-12:00");
		List<SkStuOrder> b=(List<SkStuOrder>) skStuOrderDAO.count(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "17:30-19:00"); 
		List<SkStuOrder> c=(List<SkStuOrder>) skStuOrderDAO.count(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "19:30-21:00");
		
//		getSession1().setMaxInactiveInterval(3600*5);
//		getSession1().setAttribute("flag",true);
		List<SkStuOrder> ols = skStuOrderDAO.findByProperty("skStudent", st);
		getRequest().put("ols", ols);
		getRequest().put("n1", skStuOrderDAO.cleanbyShop(a, shopId).size());
		getRequest().put("n2",skStuOrderDAO.cleanbyShop(b, shopId).size() );
		getRequest().put("n3", skStuOrderDAO.cleanbyShop(c, shopId).size());
		
		return "success";
	}

	// //修改密码
	// public String changePwd(){
	// try{
	// skStuOrder
	// skStuOrder1=skStuOrderDAO.findByUserName(skStuOrder.getUserName()).get(0);
	// skStuOrder.setUserId(skStuOrder1.getUserId());
	// skStuOrderDAO.merge(skStuOrder);
	//
	// addFieldError("change_suc",getText("changePWD_suc"));
	// }catch(Exception e){
	// addFieldError("change_err",getText("changePWD_err"));
	// }
	// return "result";
	// }

	// 登录
	@Override
	public String execute() throws Exception {
		if (skStuOrderDAO.findUserExist(skStuOrder.getOrderId(),
				skStuOrder.getSkStudent())) {
			getSession().put("user", skStuOrder);
			return SUCCESS;
		} else {
			addFieldError("message_login_error", getText("login.error"));
			return ERROR;
		}

	}

}
