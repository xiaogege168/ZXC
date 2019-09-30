package com.limao.action;


import java.util.List;

import javax.enterprise.inject.New;

import org.apache.struts2.ServletActionContext;
import org.hibernate.metamodel.domain.Superclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limao.dao.SkRecordsDAO;
import com.limao.dao.SkRepositoryDAO;
import com.limao.dao.SkShopDAO;
import com.limao.domain.SkRecords;
import com.limao.domain.SkRepository;
import com.limao.domain.SkShop;
import com.limao.domain.SkStudent;
import com.limao.domain.SkUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@Service
public class SkRecordsAction extends BaseAction implements
		ModelDriven<SkRecords> {
	
	@Autowired
	private SkRecordsDAO  skRecordsDAO ;
	@Autowired
	private SkRepositoryDAO  skRepositoryDAO ;

	private SkRecords skRecords;
	@Override
	public SkRecords getModel() {
		/*
		 * System.out.println(new SimpleDateFormat("yyyyMMdd").format(new
		 * Date())); System.out.println(new
		 * SimpleDateFormat("yyyyMMdd").format(skRecords
		 * .getOrderTime())+"#########");
		 */

		return skRecords;
	}

	public String add() {
//		SkStudent skStudent = (SkStudent) getSession1().getAttribute("user");
		
		skRecordsDAO.saveRecords();
		
		return "success";
	}

	public String del() {
		int a = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("orderId"));
		int b = (Integer) getRequest().get("orderId");
		skRecords = skRecordsDAO.findById(a);
		skRecordsDAO.delete(skRecords);
		return "success";
	}

	public String modify() {
		skRecordsDAO.merge(skRecords);
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String findAll() {
		List<SkRecords> ols = skRecordsDAO.findAll();
		getRequest().put("ols", ols);
		return "findAll";
	}
  
	@SuppressWarnings("unchecked")
	public String findBySkRepository() {
		
	  int id=Integer.parseInt(ServletActionContext.getRequest().getParameter("shopId"));
	  SkRepository sk= skRepositoryDAO.findById(id);
	  List<SkRepository> ols = skRecordsDAO.findByProperty("SkRepository", sk);
	  getRequest().put("ols", ols);
		
//		SkStudent st = (SkStudent) getSession1().getAttribute("user");
//		getSession1().setMaxInactiveInterval(3600 * 5);
//		getSession1().setAttribute("flag", true);
//		List<SkRepository> ols = skRecordsDAO.findByProperty("skStudent", st);
//		getRequest().put("ols", ols);

		return "success";
	}

	// //修改密码
	// public String changePwd(){
	// try{
	// skRecords
	// skRecords1=skRecordsDAO.findByUserName(skRecords.getUserName()).get(0);
	// skRecords.setUserId(skRecords1.getUserId());
	// skRecordsDAO.merge(skRecords);
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
