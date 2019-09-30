package com.limao.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.constraints.Null;

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

import com.limao.domain.SkShop;
import com.limao.domain.SkStudent;

/**
 * A data access object (DAO) providing persistence and search support for
 * SkStudent entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.limao.domain.SkStudent
 * @author MyEclipse Persistence Tools
 */
//@Controller
@Transactional
public class SkStudentDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SkStudentDAO.class);
	// property constants
	public static final String STU_NAME = "stuName";
	public static final String STU_PASSWORD = "stuPassword";
	public static final String AGE = "age";
	public static final String GENDER = "gender";
	public static final String PARENT_NUM = "parentNum";
	public static final String ORDER = "order";
	public static final String REAL_PRICE = "realPrice";

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
//本月学生业绩
	public List findByMonth(int shopId) { 
		log.debug("saving SkStudent instance");
		try {
			String mm=new SimpleDateFormat("MM").format(new Date());
//			SELECT * FROM test2.sk_student where shopId=2
			String queryString = "from SkStudent as model where model.createTime between '2019-"+mm+"-01' and '2019-"+mm+"-31'";
			
					Query queryObject = getCurrentSession().createQuery(queryString);
					
					List<SkStudent> ls=new ArrayList();
					List<SkStudent> lss=queryObject.list();
					if (shopId>0) {
						for (SkStudent st : lss) {
							if (st.getSkShop().getShopId().equals(shopId)) {
								ls.add(st);
							}
						}
						return ls;
					}
					
					return lss;
		} catch (RuntimeException re) {			log.error("save failed", re);
			throw re;
		}
	}
	
	
	public void save(SkStudent transientInstance) {
		log.debug("saving SkStudent instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SkStudent persistentInstance) {
		log.debug("deleting SkStudent instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SkStudent findById(java.lang.Integer id) {
		log.debug("getting SkStudent instance with id: " + id);
		try {
			SkStudent instance = (SkStudent) getCurrentSession().get(
					"com.limao.domain.SkStudent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SkStudent> findByExample(SkStudent instance) {
		log.debug("finding SkStudent instance by example");
		try {
			List<SkStudent> results = (List<SkStudent>) getCurrentSession()
					.createCriteria("com.limao.domain.SkStudent")
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
		log.debug("finding SkStudent instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SkStudent as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SkStudent> findByStuName(Object stuName) {
		return findByProperty("stuName", stuName);
	}

	public List<SkStudent> findByStuPassword(Object stuPassword) {
		return findByProperty("stuPassword", stuPassword);
	}

	public List<SkStudent> findByAge(Object age) {
		return findByProperty("age", age);
	}

	public List<SkStudent> findByGender(Object gender) {
		return findByProperty("gender", gender);
	}


	public List findAll() {
		log.debug("finding all SkStudent instances");
		try {
			String queryString = "from SkStudent order by createTime desc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SkStudent merge(SkStudent detachedInstance) {
		log.debug("merging SkStudent instance");
		try {
			SkStudent result = (SkStudent) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SkStudent instance) {
		log.debug("attaching dirty SkStudent instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SkStudent instance) {
		log.debug("attaching clean SkStudent instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SkStudentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SkStudentDAO) ctx.getBean("SkStudentDAO");
	}
	//验证
	public boolean findUserExist(String stuName, String stuPassword) {
		if (findByStuName(stuName).size()==0) {
			return false;
		}
		
		SkStudent skSt=this.findByStuName(stuName).get(0);
		
//		System.out.println(stuPassword+"==========");
//		System.out.println(skSt.getStuPassword());
//		System.out.println(stuPassword==skSt.getStuPassword());
		if (stuPassword.equals(skSt.getStuPassword())) {
			return true;
		}
		return false;
	}
}