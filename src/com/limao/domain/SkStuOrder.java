package com.limao.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SkStuOrder entity. @author MyEclipse Persistence Tools
 */
@Service
@Entity
@Table(name = "sk_stu_order", catalog = "test2")
public class SkStuOrder implements java.io.Serializable {

	// Fields

	private Integer orderId;
	@Autowired
	private SkStudent skStudent;
	private String orderTime;
	private String teachComment;
	private String ampm;
	private String today;



	public String getToday() {
		today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	/** default constructor */
	public SkStuOrder() {
	}

	/** minimal constructor */
	public SkStuOrder(SkStudent skStudent) {
		this.skStudent = skStudent;
	}

	/** full constructor */
	public SkStuOrder(SkStudent skStudent, String orderTime,
			String teachComment) {
		this.skStudent = skStudent;
		this.orderTime = orderTime;
		this.teachComment = teachComment;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "orderId", unique = true, nullable = false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER )
	@JoinColumn(name = "stuId", nullable = false )
	public SkStudent getSkStudent() {
		return this.skStudent;
	}
 
	public void setSkStudent(SkStudent skStudent) {
		this.skStudent = skStudent;
	}

	@Column(name = "orderTime", length = 22)
	public String getOrderTime() {
//		return new Timestamp(System.currentTimeMillis());
		return this.orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	@Column(name = "teachComment", length = 120)
	public String getTeachComment() {
		return this.teachComment;
	}

	public void setTeachComment(String teachComment) {
		this.teachComment = teachComment;
	}
	@Column(name = "ampm", length = 11)
	public String getAmpm() {
		return ampm;
	}

	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}

}