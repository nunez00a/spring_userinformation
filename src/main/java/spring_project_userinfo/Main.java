package spring_project_userinfo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

public class Main {

	public static void main(String[] args) 
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
		User_DAO userdao =(User_DAO)context.getBean("user_dao");
		try 
		{
			//User newuser = new User("Damn","Daniel", 12);
			
			//userdao.createuser(newuser);
			User updateuser =new User("Lizbeth","Nunez",18);
			userdao.update(updateuser);
			//userdao.delete(67);
			List<User> users = userdao.getUsers();
			
			for(int i=0;i<users.size();i++)
			{
				System.out.println(users.get(i));
			}
			User user1 = userdao.getUser();
			System.out.println("Nunez only: "+ user1);
		} catch (DataAccessException e) 
		{
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
	}

}
