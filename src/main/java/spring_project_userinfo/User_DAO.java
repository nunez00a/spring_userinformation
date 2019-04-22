package spring_project_userinfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("user_dao")
public class User_DAO 
{
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<User> getUsers()
	{
		
		return jdbc.query("select * from users ",new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				return user;
			}
			
		});
	}
	
	public boolean update(User user)
	{
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		return jdbc.update("update users set first_name=:first_name, last_name = :last_name where age=:age", params)==1;
	}
	
	public boolean delete(int age)
	{
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("age", age);
		return jdbc.update("delete from users where age = :age", params)==1;
	}
	public boolean createuser(User user)
	{
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		return jdbc.update("insert into users(first_name,last_name,age) values (:first_name, :last_name, :age)", params)==1;
	}
	
	public User getUser()
	{
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("x", "Nunez");
		return jdbc.queryForObject("select * from users where last_name= :x", params,new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				return user;
			}
			
		});
	}
}
