//package com.limao.test;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.limao.dao.SkStudentDAO;
//
//public class UsersServiceTest {
//	private static SkStudentDAO usersService;
//
//	@BeforeClasspublic
//	static void setUpBeforeClass() throws Exception {
//		try {
//			ApplicationContext ac = new ClassPathXmlApplicationContext(
//					"applicationContext.xml");
//			usersService = (UsersServiceInter) ac.getBean("usersService");
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Testpublic
//	void checkUsername() throws Exception {
//		System.out.println(usersService.checkUsername("admin"));
//	}
//
//	@Testpublic
//	void checkUsers() throws Exception {
//		Users users = new Users();
//		users.setUsername("admin");
//		users.setPwd("admin");
//		try {
//			users = usersService.checkUsers(users);
//			System.out.println(users.toString());
//		} catch (Exception e) {
//			System.out.println("异常");
//			e.printStackTrace();
//		}
//	}
//}