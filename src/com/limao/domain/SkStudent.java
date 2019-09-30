package com.limao.domain;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limao.dao.SkCourseDAO;
import com.limao.dao.SkShopDAO;

/**
 * SkStudent entity. @author MyEclipse Persistence Tools
 */
@Service
@Entity
@Table(name = "sk_student", catalog = "test2")
public class SkStudent implements java.io.Serializable {
	// Fields

	private Integer stuId;
	@Autowired
	private SkCourse skCourse;
	@Autowired
	private SkShop skShop;
//	@Autowired
//	private SkStuOrder skStuOrder;
//	public SkStuOrder getSkStuOrder() {
//		return skStuOrder;
//	}
//
//	public void setSkStuOrder(SkStuOrder skStuOrder) {
//		this.skStuOrder = skStuOrder;
//	}
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	@JoinColumn(name = "shopId")
	public SkShop getSkShop() {
		return skShop;
	}

	public void setSkShop(SkShop skShop) {
		this.skShop = skShop;
	}

	private String stuName;
	private String stuPassword;
	private Integer age;
	private String gender;
	private String createTime;
	private String others;
	@Column(name = "others")
	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	private Integer parentNum;
	private Integer realPrice;
	
	@Autowired
	private Set<SkStuOrder> skStuOrders = new HashSet<SkStuOrder>(0);

	// Constructors

	/** default constructor */
	public SkStudent() {
	}

	/** minimal constructor */
	public SkStudent(String stuName, String stuPassword, String gender) {
		this.stuName = stuName;
		this.stuPassword = stuPassword;
		this.gender = gender;
	}

	/** full constructor */
	public SkStudent(SkShop skShop,SkCourse skCourse, String stuName, String stuPassword,
			Integer age, String gender, Integer parentNum,
			Integer realPrice, Set<SkStuOrder> skStuOrders) {
		this.skCourse = skCourse;
		this.stuName = stuName;
		this.stuPassword = stuPassword;
		this.age = age;
		this.gender = gender;
		this.parentNum = parentNum;
		this.realPrice = realPrice;
		this.skStuOrders = skStuOrders;
		this.skShop=skShop;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "stu_id", unique = true, nullable = false)
	public Integer getStuId() {
		return this.stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "courseID")
	public SkCourse getSkCourse() {
		return this.skCourse;
	}

	public void setSkCourse(SkCourse skCourse) {
		this.skCourse = skCourse;
	}

	@Column(name = "stuName", nullable = false, length = 45)
	public String getStuName() {
		return this.stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	@Column(name = "stuPassword", nullable = false, length = 45)
	public String getStuPassword() {
		return this.stuPassword;
	}

	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "gender", nullable = false, length = 45)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "parentNum")
	public Integer getParentNum() {
		return this.parentNum;
	}
	@Column(name = "createTime")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setParentNum(Integer parentNum) {
		this.parentNum = parentNum;
	}

	

	@Column(name = "realPrice")
	public Integer getRealPrice() {
		return this.realPrice;
	}

	public void setRealPrice(Integer realPrice) {
		this.realPrice = realPrice;
	}
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "orderId")
//	public SkStuOrder getSkStuOrder() {
//		return skStuOrder;
//	}
//
//	public void setSkStuOrder(SkStuOrder skStuOrder) {
//		this.skStuOrder = skStuOrder;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "stuId", nullable = false)
	
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "skStudent")

	public Set<SkStuOrder> getSkStuOrders() {
		return this.skStuOrders;
	}

	public void setSkStuOrders(Set<SkStuOrder> skStuOrders) {
		this.skStuOrders = skStuOrders;
	}


}