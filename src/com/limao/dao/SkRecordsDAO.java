package com.limao.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limao.domain.SkRecords;
import com.limao.domain.SkRepository;
import com.limao.domain.SkShop;
import com.sun.mail.iap.Literal;

/**
 * A data access object (DAO) providing persistence and search support for
 * SkRecords entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.limao.domain.SkRecords
 * @author MyEclipse Persistence Tools
 */
@Service
@Transactional
public class SkRecordsDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SkRecordsDAO.class);
	// property constants
	public static final String PLUS_MOUNT = "plusMount";
	public static final String SUB_MOUNT = "subMount";
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private SkRepositoryDAO skRepositoryDAO;
	@Autowired
	private SkShopDAO shopDAO;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(SkRecords transientInstance) {
		log.debug("saving SkRecords instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		} 
	}

	public void saveRecords() { 
		Map<String, String[]>  map=ServletActionContext.getRequest().getParameterMap();
		Set<String> set = map.keySet();
		System.out.println(map.size());
		int id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("shopId"));
//		int id1 = Integer.parseInt(ServletActionContext.getRequest()
//				.getParameter("repositoryId"));
		SkShop shop = shopDAO.findById(id);
//		SkRepository skRepository =skRepositoryDAO.findById(id1);
		
//		for (String string : set) {
//			System.out.print(string + "---------->");
//			System.out.println(map.get(string)[0].isEmpty());
//		}

		for (String name : set) {
			if (name.matches("^[\u4e00-\u9fa5]*plusMount$")) {
				if (!map.get(name)[0].isEmpty()) {
					int num = Integer.parseInt(map.get(name)[0]); 
					name = name.replace("plusMount", "");
					SkRepository skRepository =skRepositoryDAO.findByPM("name", name,id);
					SkRecords transientInstance = new SkRecords(skRepository,
							new Date(), num, 0);
					this.save(transientInstance);
					
					skRepository.setAmount(skRepository.getAmount()+num);
					skRepositoryDAO.merge(skRepository);
				}

			} else if (name.matches("^[\u4e00-\u9fa5]*subMount$")) {
				if (!map.get(name)[0].isEmpty()) {
					int num = Integer.parseInt(map.get(name)[0]);
					name = name.replace("subMount", "");
					SkRepository skRepository =skRepositoryDAO.findByPM("name", name,id);
					SkRecords transientInstance = new SkRecords(skRepository,
							new Date(), 0, -num);
					this.save(transientInstance);
					
					skRepository.setAmount(skRepository.getAmount()-num);
					skRepositoryDAO.merge(skRepository);
				}
			}
		}
		

	}

	public void delete(SkRecords persistentInstance) {
		log.debug("deleting SkRecords instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkRecords findById(java.lang.Integer id) {
		log.debug("getting SkRecords instance with id: " + id);
		try {
			SkRecords instance = (SkRecords) getCurrentSession().get(
					"com.limao.domain.SkRecords", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SkRecords> findByExample(SkRecords instance) {
		log.debug("finding SkRecords instance by example");
		try {
			List<SkRecords> results = (List<SkRecords>) getCurrentSession()
					.createCriteria("com.limao.domain.SkRecords")
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
		log.debug("finding SkRecords instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SkRecords as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SkRecords> findByPlusMount(Object plusMount) {
		return findByProperty(PLUS_MOUNT, plusMount);
	}

	public List<SkRecords> findBySubMount(Object subMount) {
		return findByProperty(SUB_MOUNT, subMount);
	}

	public List findAll() {
		log.debug("finding all SkRecords instances");
		try {
			String queryString = "from SkRecords order by recordsId";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkRecords merge(SkRecords detachedInstance) {
		log.debug("merging SkRecords instance");
		try {
			SkRecords result = (SkRecords) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkRecords instance) {
		log.debug("attaching dirty SkRecords instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkRecords instance) {
		log.debug("attaching clean SkRecords instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SkRecordsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SkRecordsDAO) ctx.getBean("SkRecordsDAO");
	}
}