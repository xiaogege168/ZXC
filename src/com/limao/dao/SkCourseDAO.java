package com.limao.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

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

import com.limao.domain.SkCourse;

/**
 * A data access object (DAO) providing persistence and search support for
 * SkCourse entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.limao.domain.SkCourse
 * @author MyEclipse Persistence Tools
 */
//@Controller
@Transactional
public class SkCourseDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SkCourseDAO.class);
	// property constants
	public static final String COURSE_TIME = "courseTime";
	public static final String COURSE_PRICE = "coursePrice";
	public static final String COURSE_CONTENT = "courseContent";

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

	public void save(SkCourse transientInstance) {
		log.debug("saving SkCourse instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkCourse persistentInstance) {
		log.debug("deleting SkCourse instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkCourse findById(java.lang.Integer id) {
		log.debug("getting SkCourse instance with id: " + id);
		try {
			SkCourse instance = (SkCourse) getCurrentSession().get(
					"com.limao.domain.SkCourse", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SkCourse> findByExample(SkCourse instance) {
		log.debug("finding SkCourse instance by example");
		try {
			List<SkCourse> results = (List<SkCourse>) getCurrentSession()
					.createCriteria("com.limao.domain.SkCourse").add(create(instance))
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
		log.debug("finding SkCourse instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SkCourse as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SkCourse> findByCourseTime(Object courseTime) {
		return findByProperty(COURSE_TIME, courseTime);
	}

	public List<SkCourse> findByCoursePrice(Object coursePrice) {
		return findByProperty(COURSE_PRICE, coursePrice);
	}

	public List<SkCourse> findByCourseContent(Object courseContent) {
		return findByProperty(COURSE_CONTENT, courseContent);
	}

	public List findAll() {
		log.debug("finding all SkCourse instances");
		try {
			String queryString = "from SkCourse";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkCourse merge(SkCourse detachedInstance) {
		log.debug("merging SkCourse instance");
		try {
			SkCourse result = (SkCourse) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkCourse instance) {
		log.debug("attaching dirty SkCourse instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkCourse instance) {
		log.debug("attaching clean SkCourse instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SkCourseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SkCourseDAO) ctx.getBean("SkCourseDAO");
	}
}