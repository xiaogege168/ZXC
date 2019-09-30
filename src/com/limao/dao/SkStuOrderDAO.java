package com.limao.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.limao.domain.SkStuOrder;
import com.limao.domain.SkStudent;

/**
 * A data access object (DAO) providing persistence and search support for
 * SkStuOrder entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.limao.domain.SkStuOrder
 * @author MyEclipse Persistence Tools
 */
//@Controller
@Transactional 
public class SkStuOrderDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SkStuOrderDAO.class);
	// property constants
	public static final String TEACH_COMMENT = "teachComment";

	private SessionFactory sessionFactory;
	@Autowired
	private SkShopDAO shopDAO;
	@Autowired
	private SkStudentDAO skStudentDAO;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(SkStuOrder transientInstance) {
		log.debug("saving SkStuOrder instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkStuOrder persistentInstance) {
		log.debug("deleting SkStuOrder instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkStuOrder findById(java.lang.Integer id) {
		log.debug("getting SkStuOrder instance with id: " + id);
		try {
			SkStuOrder instance = (SkStuOrder) getCurrentSession().get(
					"com.limao.domain.SkStuOrder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SkStuOrder> findByExample(SkStuOrder instance) {
		log.debug("finding SkStuOrder instance by example");
		try {
			List<SkStuOrder> results = (List<SkStuOrder>) getCurrentSession()
					.createCriteria("com.limao.domain.SkStuOrder")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<SkStuOrder> cleanbyShop(List<SkStuOrder> list ,int shopId) {
		List<SkStuOrder> lss=new ArrayList<SkStuOrder>();
			for (SkStuOrder skStuOrder : list) {
				if (skStuOrder.getSkStudent().getSkShop().getShopId()==shopId) {
					lss.add(skStuOrder);
				}
			
		}
		return lss;
	}

	public List count(String orderTime, String ampm) {
		try {
			String queryString = "from SkStuOrder as model where model.orderTime= ? and ampm=?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, orderTime);
			queryObject.setParameter(1, ampm);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	//查询每个店当天约课
	public List findByShop(int shopId) {
		
		try {
		
		SkShop shop=shopDAO.findById(shopId);
		List<SkStudent> ls=skStudentDAO.findByProperty("skShop", shop);
		List <Integer> stuIds=new ArrayList<Integer>();
		
		for (SkStudent skStudent : ls) {
			int id=skStudent.getStuId();
			stuIds.add(id);
		}
		
		
		
		List<SkStuOrder> lss=findToday();
		
		List<SkStuOrder> lstest=new ArrayList<SkStuOrder>();
		for (SkStuOrder skStuOrder : lss) {
					
					if (stuIds.contains(skStuOrder.getSkStudent().getStuId())) {
						lstest.add(skStuOrder);
					};
		}
		
		
		return lstest;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SkStuOrder instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SkStuOrder as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SkStuOrder> findByTeachComment(Object teachComment) {
		return findByProperty(TEACH_COMMENT, teachComment);
	}

	public List findToday() {
		log.debug("finding all SkStuOrder instances");
		try {
			String timeString=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			String queryString = "from SkStuOrder  order by orderTime desc where orderTime=today ";
			
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll() {
		log.debug("finding all SkStuOrder instances");
		try {
			String queryString = "from SkStuOrder order by orderTime desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public SkStuOrder findLast(int stuId) {
		try {
			String queryString = "from SkStuOrder where stuId=? order by orderId desc limit 1";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, stuId);
			return  (SkStuOrder) (queryObject.list().size()==0?null: queryObject.list().get(0));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	} 
	
	

	public SkStuOrder merge(SkStuOrder detachedInstance) {
		log.debug("merging SkStuOrder instance");
		try {
			SkStuOrder result = (SkStuOrder) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkStuOrder instance) {
		log.debug("attaching dirty SkStuOrder instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkStuOrder instance) {
		log.debug("attaching clean SkStuOrder instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SkStuOrderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SkStuOrderDAO) ctx.getBean("SkStuOrderDAO");
	}

	public boolean findUserExist(Integer orderId, SkStudent skStudent) {
		// TODO
		return true;
	}
}