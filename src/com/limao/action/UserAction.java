package com.limao.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limao.dao.SkShopDAO;
import com.limao.dao.SkUserDAO;
import com.limao.domain.SkShop;
import com.limao.domain.SkUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
@Service
public class UserAction extends BaseAction implements ModelDriven<SkUser> {
	@Autowired
	private SkUser skUser;
	@Autowired
	private SkUserDAO skUserDao;
	@Autowired
	private SkShop skShop;
	@Autowired
	private SkShopDAO skShopDAO;

	@Override
	public SkUser getModel() {
		return skUser;
	}

	public String add() {
		// System.out.println( getRequest().get("shopAddr").toString());
		int sid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("shopId"));
		skShop = (SkShop) skShopDAO.findById(sid);
		skUser.setSkShop(skShop);
		skUserDao.save(skUser);
		return "FF";
	}

	// 删除用户
	public String del() {
		int id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("userId"));
		skUser = skUserDao.findById(id);
		skUserDao.delete(skUser);
		return "FF";
	}

	// 准备修改用户
	public String findById() {
		int id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("userId"));

		int id1 = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("shopId"));
		skShop = skShopDAO.findById(id1);
		skUser = skUserDao.findById(id);
		skUser.setSkShop(skShop);
		getRequest().put("skUser", skUser);
		return "findById";
	}

	// 修改用户
	public String modify() {
		skUser.setSkShop(skShopDAO.findById(Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("shopId"))));
		
		skUserDao.merge(skUser);
		return "FF";
	}

	@SuppressWarnings("unchecked")
	public String findAll() {

		getRequest().put("users", skUserDao.findAll());
		return "findAll";
	}

	// 修改密码
	public String changePwd() {
		try {
			SkUser skUser1 = skUserDao.findByUserName(skUser.getUserName())
					.get(0);
			skUser.setUserId(skUser1.getUserId());
			skUserDao.merge(skUser);

			addFieldError("change_suc", getText("changePWD_suc"));
		} catch (Exception e) {
			addFieldError("change_err", getText("changePWD_err"));
		}
		return "result";
	}

	// 登录
	@Override
	public String execute() throws Exception {
		if (skUserDao.findUserExist(skUser.getUserName(),
				skUser.getUserPassword())) {
			getSession().put("user", skUser);
			return SUCCESS;
		} else {
			addFieldError("message_login_error", getText("login.error"));
			return ERROR;
		}

	}

}
