/**
* 项目名:		Android_User_Database
* 包名:		
* 文件名:		User.java
* 创建时间:	2019年5月8日
* 
* @author:	xiatom
* 描述:		
* 
*
**/
public class User {
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", longitude=" + longitude + ", alititude=" + alititude
				+ "]";
	}
	private String name;
	private String password;
	private String longitude;
	private String alititude;
	public User(String name,String longitude,String alitutde) {
		this.name = name;
		this.longitude  = longitude;
		this.alititude = alitutde;
	}
	public User(String name,String password) {
		this.name = name;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAlititude() {
		return alititude;
	}
	public void setAlititude(String alititude) {
		this.alititude = alititude;
	}
	
}
