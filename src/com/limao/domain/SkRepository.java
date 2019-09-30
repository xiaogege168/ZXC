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
 * SkRepository entity. @author MyEclipse Persistence Tools
 */
@Service  
@Entity
@Table(name = "sk_repository", catalog = "test2")
public class SkRepository implements java.io.Serializable {

	// Fields

	private Integer repositoryId;
	@Autowired
	private SkShop skShop;
	private String name;
	private Integer amount;
	@Autowired
	private Set<SkRecords> skRecords=new HashSet<SkRecords>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "goodsId")
	public Set<SkRecords> getSkRecords() {
		return skRecords;
	}

	public void setSkRecords(Set<SkRecords> skRecords) {
		this.skRecords = skRecords;
	}

	// Constructors


	/** default constructor */
	public SkRepository() {
	}

	/** full constructor */
	public SkRepository(SkShop skShop, String name, Integer amount) {
		this.skShop = skShop;
		this.name = name;
		this.amount = amount;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "repositoryId", unique = true, nullable = false)
	public Integer getRepositoryId() {
		return this.repositoryId;
	}

	public void setRepositoryId(Integer repositoryId) {
		this.repositoryId = repositoryId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shopId")
	public SkShop getSkShop() {
		return this.skShop;
	}

	public void setSkShop(SkShop skShop) {
		this.skShop = skShop;
	}

	@Column(name = "name", length = 22)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "amount")
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}