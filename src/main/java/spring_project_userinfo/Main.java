package spring_project_userinfo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) 
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
		User_DAO userdao =(User_DAO)context.getBean("user_dao");
		List<User> users = userdao.getUsers();
		
		for(int i=0;i<users.size();i++)
		{
			System.out.println(users.get(i));
		}
	}

}
