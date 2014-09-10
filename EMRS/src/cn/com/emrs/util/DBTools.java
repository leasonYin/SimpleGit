package cn.com.emrs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBTools {

	//数据库驱动
	private static String driver = "com.mysql.jdbc.Driver";
	
	// URL指向要访问的数据库名emrs
	private static String url = "jdbc:mysql://127.0.0.1:3306/emrs?characterEncoding=utf8";
	
	// MySQL配置时的用户名
	private static String user = "root";

	// Java连接MySQL配置时的密码
	private static String password = "1";

	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("can`t find the driver: " + driver);
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DataBase connect failed !");
			e.printStackTrace();
		}
		
		return conn;
	}


}
