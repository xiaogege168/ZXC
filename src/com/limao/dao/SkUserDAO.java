package com.limao.dao;

import java.util.List;
import java.util.Set;
import java.util.jar.Attributes.Name;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limao.domain.SkUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * SkUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.limao.domain.SkUser
 * @author MyEclipse Persistence Tools
 */
//@Controller
@Transactional
public class SkUserDAO {
	private static final Logger log = LoggerFactory.getLogger(SkUserDAO.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String USER_PASSWORD = "userPassword";
	public static final String SFZ_NAME = "sfzName";
	public static final String PHONE_NUM = "phoneNum";
	public static final String JOB = "job";

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

	public void save(SkUser transientInstance) {
		log.debug("saving SkUser instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkUser persistentInstance) {
		log.debug("deleting SkUser instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkUser findById(java.lang.Integer id) {
		log.debug("getting SkUser instance with id: " + id);
		try {
			SkUser instance = (SkUser) getCurrentSession().get(
					"com.limao.domain.SkUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SkUser> findByExample(SkUser instance) {
		log.debug("finding SkUser instance by example");
		try {
			List<SkUser> results = (List<SkUser>) getCurrentSession()
					.createCriteria("com.limao.domain.SkUser").add(create(instance))
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
		log.debug("finding SkUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SkUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SkUser> findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List<SkUser> findByUserPassword(Object userPassword) {
		return findByProperty(USER_PASSWORD, userPassword);
	}

	public List<SkUser> findBySfzName(Object sfzName) {
		return findByProperty(SFZ_NAME, sfzName);
	}

	public List<SkUser> findByPhoneNum(Object phoneNum) {
		return findByProperty(PHONE_NUM, phoneNum);
	}

	public List<SkUser> findByJob(Object job) {
		return findByProperty(JOB, job);
	}

	public List findAll() {
		log.debug("finding all SkUser instances");
		try {
			String queryString = "from SkUser order by shopId asc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkUser merge(SkUser detachedInstance) {
		log.debug("merging SkUser instance");
		try {
			SkUser result = (SkUser) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkUser instance) {
		log.debug("attaching dirty SkUser instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkUser instance) {
		log.debug("attaching clean SkUser instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SkUserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SkUserDAO) ctx.getBean("SkUserDAO");
	}
//验证
	public boolean findUserExist(String userName, String userPassword) {
		SkUser skUser=this.findByUserName(userName).get(0);
				if (userPassword==skUser.getUserPassword()) {
					return true;
				}
		return false;
	}
}