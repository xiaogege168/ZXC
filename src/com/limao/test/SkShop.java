//package com.limao.test;
//
//import java.util.HashSet;
//import java.util.Set;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// * SkShop entity. @author MyEclipse Persistence Tools
// */
//@Entity
//@Table(name = "sk_shop", catalog = "test2")
//public class SkShop implements java.io.Serializable {
//
//	// Fields
//
//	private Integer shopId;
//	private SkUser skUser;
//	private String address;
//	private Integer monthIncome;
//	private Set<SkRepository> skRepositories = new HashSet<SkRepository>(0);
//	private Set<SkUser> skUsers = new HashSet<SkUser>(0);
//	private Set<SkStudent> skStudents = new HashSet<SkStudent>(0);
//
//	// Constructors
//
//	/** default constructor */
//	public SkShop() {
//	}
//
//	/** full constructor */
//	public SkShop(SkUser skUser, String address, Integer monthIncome,
//			Set<SkRepository> skRepositories, Set<SkUser> skUsers,
//			Set<SkStudent> skStudents) {
//		this.skUser = skUser;
//		this.address = address;
//		this.monthIncome = monthIncome;
//		this.skRepositories = skRepositories;
//		this.skUsers = skUsers;
//		this.skStudents = skStudents;
//	}
//
//	// Property accessors
//	@GenericGenerator(name = "generator", strategy = "increment")
//	@Id
//	@GeneratedValue(generator = "generator")
//	@Column(name = "shopId", unique = true, nullable = false)
//	public Integer getShopId() {
//		return this.shopId;
//	}
//
//	public void setShopId(Integer shopId) {
//		this.shopId = shopId;
//	}
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "leaderId")
//	public SkUser getSkUser() {
//		return this.skUser;
//	}
//
//	public void setSkUser(SkUser skUser) {
//		this.skUser = skUser;
//	}
//
//	@Column(name = "address", length = 45)
//	public String getAddress() {
//		return this.address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	@Column(name = "monthIncome")
//	public Integer getMonthIncome() {
//		return this.monthIncome;
//	}
//
//	public void setMonthIncome(Integer monthIncome) {
//		this.monthIncome = monthIncome;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "skShop")
//	public Set<SkRepository> getSkRepositories() {
//		return this.skRepositories;
//	}
//
//	public void setSkRepositories(Set<SkRepository> skRepositories) {
//		this.skRepositories = skRepositories;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "skShop")
//	public Set<SkUser> getSkUsers() {
//		return this.skUsers;
//	}
//
//	public void setSkUsers(Set<SkUser> skUsers) {
//		this.skUsers = skUsers;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "skShop")
//	public Set<SkStudent> getSkStudents() {
//		return this.skStudents;
//	}
//
//	public void setSkStudents(Set<SkStudent> skStudents) {
//		this.skStudents = skStudents;
//	}
//
//}