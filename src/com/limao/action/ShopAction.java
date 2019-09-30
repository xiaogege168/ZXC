package com.limao.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.limao.dao.SkShopDAO;
import com.limao.dao.SkStudentDAO;
import com.limao.dao.SkUserDAO;
import com.limao.domain.SkShop;
import com.limao.domain.SkStudent;
import com.limao.domain.SkUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ShopAction extends BaseAction implements ModelDriven<SkShop> {
	@Autowired
	private SkShop skShop;
	@Autowired
	private SkShopDAO skShopDAO;
	@Autowired
	private SkUserDAO skUserDAO;
	@Autowired
	private SkStudentDAO skStudentDAO;
	@Autowired
	private SkUser skUser;

	private int leaderId;

	private Set<SkUser> skUsers = new HashSet<SkUser>(0);

	@Override
	public SkShop getModel() {
		return skShop;
	}

	public SkUser getSkUser() {
		return skUserDAO.findById(leaderId);
	}

	public void setSkUser(SkUser skUser) {
		this.skUser = skUser;
	}

	public Set<SkUser> getSkUsers() {
		return skUsers;
	}

	public void setSkUsers(Set<SkUser> skUsers) {
		this.skUsers = skUsers;
	}

	public String add() {
		skShopDAO.save(skShop);
		return "addSuccess";
	}
	
	

	public String del() {
		skShopDAO.delete(skShop);
		return "delSuccess";
	}

	public String modify() {
		skShopDAO.merge(skShop);
		return "modifySuccess";
	}

	// 本月所有店的收入
	public String getMonthIncome() {
		@SuppressWarnings("unchecked")
		List<SkStudent> lss = skStudentDAO.findByMonth(0);
		int all = 0;
		for (SkStudent skStudent : lss) {
			all += skStudent.getRealPrice();
		}
		getRequest().put("monthIncome", all);
		return "findAllSuccess";
	}

	// 本月某个店的收入----------
	public int getMonthIncome0(int shopId) {
		List<SkStudent> lss = skStudentDAO.findByMonth(shopId);
		int all = 0;
		for (SkStudent skStudent : lss) {
			all += skStudent.getRealPrice();
		}
		// getRequest().put("monthIncome0", all);
		// SkShop shop=skShopDAO.findById(shopId);
		//
		// Set<SkStudent> lst=shop.getSkStudents();
		//
		// for (SkStudent skStudent : lst) {
		//
		// }

		return all;
	}

	@SuppressWarnings("unchecked")
	public String findAll() {
		@SuppressWarnings("unchecked")
		List<SkShop> ls = skShopDAO.findAll();
		for (SkShop skShop : ls) {
			int a = skShop.getShopId();
			int b = getMonthIncome0(a);
			skShop.setMonthIncome(b);
//			不用merge？提交还会出错
//			skShopDAO.merge(skShop);
		 
		}

		getRequest().put("shopList", ls);
		getMonthIncome();
		// skUser = skShopDAO.getLeader(skShop.getShopId());
		// List<SkUser> skUsers = null;
		// for (SkShop skShop : ls) {
		// skUsers.add(skShopDAO.getLeader(skShop.getShopId()));
		// }
		// getRequest().put("skUsers", skUsers);

		return "findAllSuccess";
	}

	public String findLeader() {
		int leaderId = skShopDAO.getLeaderId(skShop.getShopId());
		skUser = skUserDAO.findById(leaderId);
		return "findLeaderSuccess";
	}

	// //修改密码
	// public String changePwd(){
	// try{
	// SkShop skShop1=skShopDAO.findByUserName(skShop.getUserName()).get(0);
	// skShop.setUserId(skShop1.getUserId());
	// skShopDAO.merge(skShop);
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
		if (skShopDAO.findUserExist(skShop.getShopId(), skShop.getAddress())) {
			getSession().put("user", skShop);
			return SUCCESS;
		} else {
			addFieldError("message_login_error", getText("login.error"));
			return ERROR;
		}

	}

}
