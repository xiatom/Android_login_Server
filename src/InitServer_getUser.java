import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* 项目名:		Android_User_Database
* 包名:		
* 文件名:		InitServer_getUser.java
* 创建时间:	2019年5月8日
* 
* @author:	xiatom
* 描述:		
* 
*
**/
public class InitServer_getUser {
	private Connection connection;
	public InitServer_getUser(){
		System.out.println("init");
		connection = getCon();
	}
	
	public Connection getCon() {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/android_db";
			connection = DriverManager.getConnection(url,"root","1023");
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}
	
	public List<User> getUserList(){
		List<User> user = new ArrayList<>();
		Connection con = connection;
		String sql = "select name ,password from userinfo";
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				user.add(new User(rs.getString("name"), rs.getString("password")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user;	
	}
	
	public User getUserLocation(String name){
		Connection con = connection;
		String sql = "select longitude ,latitude from userinfo where name = ?";
		PreparedStatement ps = null;
		ResultSet rs;
		User user = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next())
			user = new User(name,rs.getString("longitude"), rs.getString("latitude"));
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println(user);
		return user;	
	}
	
	public Boolean insertUser(User user) {
		Connection con = connection;
		String sql = "insert into userinfo(name,password) values (?,?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}		
		return true;
	}
	
	
	public Boolean insertUserWithLocation(User user) {
		Connection con = connection;
		String sql = "update userinfo set longitude = ?,latitude = ? where name=?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getLongitude());
			ps.setString(2, user.getAlititude());
			ps.setString(3, user.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}		
		return true;
	}
}
