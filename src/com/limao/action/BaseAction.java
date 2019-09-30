package com.limao.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class BaseAction extends ActionSupport {

	protected String target;

	public Map getRequest(){
		return (Map)ActionContext.getContext().get("request");
	}
	
	public Map getSession(){
		return ActionContext.getContext().getSession();
	}
	
	public HttpSession getSession1(){
		return ServletActionContext.getRequest().getSession();
	} 
	
	public Map getApplication(){
		return ActionContext.getContext().getApplication();
	}
	
	public HttpServletResponse getResponse(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		return response;
	}
	
	
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	
	
}
