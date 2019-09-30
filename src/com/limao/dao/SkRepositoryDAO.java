package com.limao.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.limao.domain.SkRepository;


/**
 * A data access object (DAO) providing persistence and search support for
 * SkRepository entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.limao.domain.SkRepository
 * @author MyEclipse Persistence Tools
 */
@Resource
@Transactional
public class SkRepositoryDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SkRepositoryDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String AMOUNT = "amount";

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

	public void save(SkRepository transientInstance) {
		log.debug("saving SkRepository instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkRepository persistentInstance) {
		log.debug("deleting SkRepository instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkRepository findById(java.lang.Integer id) {
		log.debug("getting SkRepository instance with id: " + id);
		try {
			SkRepository instance = (SkRepository) getCurrentSession().get(
					"com.limao.domain.SkRepository", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SkRepository> findByExample(SkRepository instance) {
		log.debug("finding SkRepository instance by example");
		try {
			List<SkRepository> results = (List<SkRepository>) getCurrentSession()
					.createCriteria("com.limao.domain.SkRepository")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SkRepository instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SkRepository as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	 
	public SkRepository findByPM(String propertyName, Object value,int shopId) {
		try {
			String queryString = "from SkRepository as model where model."
					+ propertyName + "= ? and shopId=?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setParameter(1, shopId);
			return (SkRepository)queryObject.list().get(0);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	

	public List<SkRepository> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<SkRepository> findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List findAll() { 
		log.debug("finding all SkRepository instances");
		try {
			String queryString = "from SkRepository order by shopId";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkRepository merge(SkRepository detachedInstance) {
		log.debug("merging SkRepository instance");
		try {
			SkRepository result = (SkRepository) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkRepository instance) {
		log.debug("attaching dirty SkRepository instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkRepository instance) {
		log.debug("attaching clean SkRepository instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SkRepositoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SkRepositoryDAO) ctx.getBean("SkRepositoryDAO");
	}
}