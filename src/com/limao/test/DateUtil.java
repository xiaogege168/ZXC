package com.limao.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.New;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.limao.dao.SkRepositoryDAO;
import com.limao.dao.SkShopDAO;
import com.limao.domain.SkRepository;
import com.limao.domain.SkShop;

public class DateUtil {
	public static void main(String[] args) {
//		Date d=new Date();
//		DateFormat df=DateFormat.getInstance();
//		sessionFactory=new AnnotationConfiguration().configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();///注意是AnnotationConfiguration，而不是Configuration 
//		
//		System.out.println(DateFormat.getInstance().format(new Date()));
//		System.out.println(DateFormat.getInstance().getCalendar().get(6));
//		System.out.println(new SimpleDateFormat("MM").format(new Date()));
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml"); 
		SkShopDAO shopDAO = (SkShopDAO) ac.getBean("SkShopDAO");
		SkRepositoryDAO skRepositoryDAO = (SkRepositoryDAO) ac.getBean("SkRepositoryDAO");
		System.out.println("");
		SkRepository skRepository=skRepositoryDAO.findById(1);
		List<SkShop> ls=shopDAO.findAll();
		for (SkShop skShop : ls) {
			Set<SkRepository> skRepositories =skShop.getSkRepository();
			System.out.println(skShop.getAddress()+"\n\r");
					for (SkRepository skRepository2 : skRepositories) {
						System.out.print(skRepository2.getName()+"\t");
						System.out.println(skRepository2.getAmount());
					}
		}
		System.out.println("");
	}
//	红色护具   plusMount
//	
//	红色护具subMount
//	
	
	
}
