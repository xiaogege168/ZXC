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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SkCourse entity. @author MyEclipse Persistence Tools
 */
@Service
@Entity
@Table(name = "sk_course", catalog = "test2")
public class SkCourse implements java.io.Serializable {

	// Fields   <td>${cstm.skCourse.courseTime}

	private Integer courseId;
	private String courseTime;
	private Integer coursePrice;
	private String courseContent;
	@Autowired
	private Set<SkStudent> skStudents = new HashSet<SkStudent>(0);

	// Constructors

	/** default constructor */
	public SkCourse() {
	}

	/** full constructor */
	public SkCourse(String courseTime, Integer coursePrice,
			String courseContent, Set<SkStudent> skStudents) {
		this.courseTime = courseTime;
		this.coursePrice = coursePrice;
		this.courseContent = courseContent;
		this.skStudents = skStudents;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "courseID", unique = true, nullable = false)
	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Column(name = "courseTime", length = 45)
	public String getCourseTime() {
		return this.courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	@Column(name = "coursePrice")
	public Integer getCoursePrice() {
		return this.coursePrice;
	}

	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}

	@Column(name = "courseContent", length = 100)
	public String getCourseContent() {
		return this.courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="courseID")
	public Set<SkStudent> getSkStudents() {
		return this.skStudents;
	}

	public void setSkStudents(Set<SkStudent> skStudents) {
		this.skStudents = skStudents;
	}

}