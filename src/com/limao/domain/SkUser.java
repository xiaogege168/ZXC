package com.limao.domain;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SkUser entity. @author MyEclipse Persistence Tools
 */
@Service
@Entity
@Table(name = "sk_user", catalog = "test2")
public class SkUser implements java.io.Serializable {

	// Fields

	private Integer userId;
	@Autowired
	private SkShop skShop;
	private String userName;
	private String userPassword;
	private String sfzName;
	private String phoneNum;
	private String job;

	// Constructors

	/** default constructor */
	public SkUser() {
	}

	/** minimal constructor */
	public SkUser(SkShop skShop, String userName, String userPassword,
			String sfzName, String phoneNum, String job) {
		this.skShop = skShop;
		this.userName = userName;
		this.userPassword = userPassword;
		this.sfzName = sfzName;
		this.phoneNum = phoneNum;
		this.job = job;
	}


	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne
	@JoinColumn(name = "shopId", nullable = false)
	public SkShop getSkShop() {
		return this.skShop;
	}

	public void setSkShop(SkShop skShop) {
		this.skShop = skShop;
	}

	@Column(name = "userName", nullable = false, length = 11)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "userPassword", nullable = false, length = 45)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "sfzName", nullable = false, length = 11)
	public String getSfzName() {
		return this.sfzName;
	}

	public void setSfzName(String sfzName) {
		this.sfzName = sfzName;
	}

	@Column(name = "phoneNum", nullable = false, length = 45)
	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Column(name = "job", nullable = false, length = 11)
	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}


}