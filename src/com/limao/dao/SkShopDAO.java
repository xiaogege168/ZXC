package com.limao.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limao.domain.SkShop;
import com.limao.domain.SkUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * SkShop entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.limao.domain.SkShop
 * @author MyEclipse Persistence Tools
 */
//@Controller
@Transactional
public class SkShopDAO {
	private static final Logger log = LoggerFactory.getLogger(SkShopDAO.class);
	// property constants
	public static final String ADDRESS = "address";
	@Autowired
	private SkUserDAO skUserDAO;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(SkShop transientInstance) {
		log.debug("saving SkShop instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkShop persistentInstance) {
		log.debug("deleting SkShop instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkShop findById(java.lang.Integer id) {
		log.debug("getting SkShop instance with id: " + id);
		try {
			SkShop instance = (SkShop) getCurrentSession().get(
					"com.limao.domain.SkShop", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SkShop> findByExample(SkShop instance) {
		log.debug("finding SkShop instance by example");
		try {
			List<SkShop> results = (List<SkShop>) getCurrentSession()
					.createCriteria("com.limao.domain.SkShop").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SkShop instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SkShop as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			System.out.println(queryObject.list().size());
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SkShop> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findAll() {
		log.debug("finding all SkShop instances");
		try {
			String queryString = "from SkShop order by shopId asc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkShop merge(SkShop detachedInstance) {
		log.debug("merging SkShop instance");
		try {
			SkShop result = (SkShop) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkShop instance) {
		log.debug("attaching dirty SkShop instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkShop instance) {
		log.debug("attaching clean SkShop instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SkShopDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SkShopDAO) ctx.getBean("SkShopDAO");
	}

	public boolean findUserExist(Integer shopId, String address2) {
		// TODO
		return true;
	}
	
	public int getLeaderId(int shopId) {
//		String sqlString="from sk_shop where shopId="+shopId;
		SkShop skShop=findById(shopId);
		
		
//		SkUser skk=(SkUser) getCurrentSession().createCriteria(sqlString).list().get(0);
//		int userId =skk.getUserId();
//		System.out.println(userId+"==================");
		return skShop.getSkUser().getUserId();
	}
}