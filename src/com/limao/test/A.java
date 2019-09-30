package com.limao.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;








import com.limao.action.ShopAction;
import com.limao.dao.SkCourseDAO;
import com.limao.dao.SkStuOrderDAO;
import com.limao.dao.SkStudentDAO;
import com.limao.dao.SkUserDAO;
import com.limao.domain.SkCourse;
import com.limao.domain.SkStuOrder;
import com.limao.domain.SkStudent;
import com.limao.domain.SkUser;



public class A {

	public static void main(String[] args) {
//		System.out.println(new Date());
//		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
//		System.out.println(ft.format(new Date()));
//		
//		System.out.println(new Timestamp(System.currentTimeMillis()));
		
//		String s="红色护具plusMount";
//		System.out.println(Pattern.matches("plusMount$", s));;
//		
//		System.out.println(s.matches("^[\u4e00-\u9fa5]*plusMount$"));
//		
//		System.out.println(s.replace("plusMount", ""));
		
//		SessionFactory sessionFactory 
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
		 sessionFactory.getCurrentSession().beginTransaction();
		 SkStuOrderDAO skStuOrderDAO=(SkStuOrderDAO) ac.getBean("SkStuOrderDAO");
		 List<SkStuOrder> ls=skStuOrderDAO.findToday();
		 for (SkStuOrder skStuOrderDAO2 : ls) {
			System.out.println(skStuOrderDAO2.getOrderTime()+"%%%");
		}
		 
		 
//		 SkUserDAO skUserDAO=(SkUserDAO) ac.getBean("SkUserDAO");
//		 SkUser skUser= skUserDAO.findById(1);
//		 
//		 SkStudentDAO studentDAO=(SkStudentDAO) ac.getBean("SkStudentDAO");
//		 SkStudent skStudent=studentDAO.findById(2);
//		 
//		 SkStuOrderDAO skStuOrderDAO=(SkStuOrderDAO) ac.getBean("SkStuOrderDAO");
//		 SkStuOrder skStuOrder=new SkStuOrder(skStudent);
//		 skStuOrderDAO.save(skStuOrder);
//		 
//		 SkCourseDAO skCourseDAO=(SkCourseDAO) ac.getBean("SkCourseDAO");
//		 List<SkCourse>  skc=skCourseDAO.findAll();
//		 for (SkCourse skCourse : skc) {
//			System.out.println(skCourse.getCourseTime());
//			System.out.println(skCourse.getCourseContent());
//			System.out.println(skCourse.getCoursePrice());
//			System.out.println("");
//		}
//		 
//		 try {
//			ShopAction shopAction = (ShopAction) ac.getBean("shopAction");
//			System.out.println(shopAction.execute());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("0000000000000000");
//			e.printStackTrace();
//		}
//		 
		
		 
		 
//		int a= skUserDAO.findAll().size();
//		System.out.println(a);
		 
	}

}


