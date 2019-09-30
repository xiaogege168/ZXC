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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.limao.dao.SkShopDAO;

/**
 * SkShop entity. @author MyEclipse Persistence Tools
 */
@Service
@Entity
@Table(name = "sk_shop", catalog = "test2")
public class SkShop implements java.io.Serializable {

	@Autowired
	SkShopDAO skShopDAO;
	private Integer shopId;
	private Integer monthIncome;
	@Autowired
	private SkUser skUser;
	@Autowired
	private Set<SkRepository> skRepository=new HashSet<SkRepository>(0);
	
	private String address;
	@Autowired
	private Set<SkUser> skUsers = new HashSet<SkUser>(0);
	@Autowired
	private Set<SkStudent> skStudents = new HashSet<SkStudent>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "shopId")
	public Set<SkStudent> getSkStudents() {
		return skStudents;
	}

	public void setSkStudents(Set<SkStudent> skStudents) {
		this.skStudents = skStudents;
	}

	/** default constructor */
	public SkShop() {
	}

	

	public SkShop( Integer shopId, Integer monthIncome,
			SkUser skUser, Set<SkRepository> skRepository, String address,
			Set<SkUser> skUsers, Set<SkStudent> skStudents) {
		super();
		this.shopId = shopId;
		this.monthIncome = monthIncome;
		this.skUser = skUser;
		this.skRepository = skRepository;
		this.address = address;
		this.skUsers = skUsers;
		this.skStudents = skStudents;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "shopId", unique = true, nullable = false)
	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@OneToOne
	@JoinColumn(name = "leaderId")
	public SkUser getSkUser() {
		return skUser;
		// if (shopId==null) {
		// return skUser;
		// }
		// return skShopDAO.getLeader(shopId);
	}

	public void setSkUser(SkUser skUser) {
		this.skUser = skUser;
	}

	@Column(name = "address", length = 45)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "skShop")
	public Set<SkUser> getSkUsers() {
		return this.skUsers;
	}

	public void setSkUsers(Set<SkUser> skUsers) {
		this.skUsers = skUsers;
	}

	public int getMonthIncome() {
		return monthIncome;
	}
	public void setMonthIncome(int monthIncome) {
		this.monthIncome = monthIncome;
	}

	
	public void setSkRepository(Set<SkRepository> skRepository) {
		this.skRepository = skRepository;
	}
	@OneToMany(mappedBy="skShop",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<SkRepository> getSkRepository() {
		return skRepository;
	}

}