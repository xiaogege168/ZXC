package com.limao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;

/**
 * SkRecords entity. @author MyEclipse Persistence Tools
 */
@Service
@Entity
@Table(name = "sk_records", catalog = "test2")
public class SkRecords implements java.io.Serializable {

	// Fields

	private Integer recordsId;
	private SkRepository skRepository;
	private Date createTime;
	private Integer plusMount;
	private Integer subMount;

	// Constructors

	/** default constructor */
	public SkRecords() {
	}

	/** full constructor */
	public SkRecords(SkRepository skRepository, Date createTime,
			Integer plusMount, Integer subMount) {
		this.skRepository = skRepository;
		this.createTime = createTime;
		this.plusMount = plusMount;
		this.subMount = subMount;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "recordsId", unique = true, nullable = false)
	public Integer getRecordsId() {
		return this.recordsId;
	}

	public void setRecordsId(Integer recordsId) {
		this.recordsId = recordsId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goodsId")
	public SkRepository getSkRepository() {
		return this.skRepository;
	}

	public void setSkRepository(SkRepository skRepository) {
		this.skRepository = skRepository;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createTime", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "plusMount")
	public Integer getPlusMount() {
		return this.plusMount;
	}

	public void setPlusMount(Integer plusMount) {
		this.plusMount = plusMount;
	}

	@Column(name = "subMount")
	public Integer getSubMount() {
		return this.subMount;
	}

	public void setSubMount(Integer subMount) {
		this.subMount = subMount;
	}

}