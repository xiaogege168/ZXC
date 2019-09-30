package com.limao.action;


import java.util.List;

import javax.enterprise.inject.New;

import org.apache.struts2.ServletActionContext;
import org.hibernate.metamodel.domain.Superclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limao.dao.SkRepositoryDAO;
import com.limao.dao.SkShopDAO;
import com.limao.domain.SkRepository;
import com.limao.domain.SkShop;
import com.limao.domain.SkStudent;
import com.limao.domain.SkUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@Service
public class SkRepositoryAction extends BaseAction implements
		ModelDriven<SkRepository> {
	@Autowired
	private SkRepository skRepository;
	@Autowired
	private SkRepositoryDAO skRepositoryDAO;
	@Autowired
	private SkShopDAO skShopDAO  ;

	@Override
	public SkRepository getModel() {
		/*
		 * System.out.println(new SimpleDateFormat("yyyyMMdd").format(new
		 * Date())); System.out.println(new
		 * SimpleDateFormat("yyyyMMdd").format(skRepository
		 * .getOrderTime())+"#########");
		 */

		return skRepository;
	}

	public String add() {
		skRepositoryDAO.save(skRepository);
		return "success";
	}
	public String save() {
//		int id=(Integer)getRequest().get("skRepository.repositoryId");
//		SkRepository skRepository =  skRepositoryDAO.findById(id);
		skRepositoryDAO.save(skRepository);
		return "success";
	}
 
	public String del() {
		int a = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("orderId"));
		int b = (Integer) getRequest().get("orderId");
		skRepository = skRepositoryDAO.findById(a);
		skRepositoryDAO.delete(skRepository);
		return "success";
	}

	public String modify() {
		skRepositoryDAO.merge(skRepository);
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String findAll() {
		List<SkRepository> ols = skRepositoryDAO.findAll();
		getRequest().put("ols", ols);
		return "success";
	}
  
	@SuppressWarnings("unchecked")
	public String findByShop() {
		
	  int id=Integer.parseInt(ServletActionContext.getRequest().getParameter("shopId"));
	  SkShop shop= skShopDAO.findById(id);
	  List<SkRepository> ols = skRepositoryDAO.findByProperty("skShop", shop);
	  getRequest().put("ols", ols);
		
//		SkStudent st = (SkStudent) getSession1().getAttribute("user");
//		getSession1().setMaxInactiveInterval(3600 * 5);
//		getSession1().setAttribute("flag", true);
//		List<SkRepository> ols = skRepositoryDAO.findByProperty("skStudent", st);
//		getRequest().put("ols", ols);

		return "success";
	}

	// //修改密码
	// public String changePwd(){
	// try{
	// skRepository
	// skRepository1=skRepositoryDAO.findByUserName(skRepository.getUserName()).get(0);
	// skRepository.setUserId(skRepository1.getUserId());
	// skRepositoryDAO.merge(skRepository);
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
		return "";
	}
}
