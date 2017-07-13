package com.summit.demo.addvcdDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Dao {
	/**
	 * @param args
	 */
	// 驱动程序就是之前在classpath中配置的JDBC的驱动程序的JAR 包中
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
	// 连接地址是由各个数据库生产商单独提供的，所以需要单独记住
	public static final String DBURL = "jdbc:mysql://localhost:3306/watf?rewriteBatchedStatements=true";
	// 连接数据库的用户名
	public static final String DBUSER = "root";
	// 连接数据库的密码
	public static final String DBPASS = "agree123";

	static Connection con = null; // 表示数据库的连接对象

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DBDRIVER); // 1、使用CLASS 类加载驱动程序
		con = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 2、连接数据库
		return con;
	}

	public static void batcAdd(List<AddvcdBean> beanList) throws SQLException, ClassNotFoundException {
		Connection con = null; // 表示数据库的连接对象
		PreparedStatement pstmt = null; // 表示数据库更新操作

		String sql = "insert into t_addvcd_all values(?,?)";

		Class.forName(DBDRIVER); // 1、使用CLASS 类加载驱动程序

		System.out.println(sql);
		con = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 2、连接数据库
		con.setAutoCommit(false);

		pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); // 使用预处理的方式创建对象

		for (AddvcdBean bean : beanList) {
			pstmt.setString(1, bean.getAddvcd()); // 第一个？号的内容
			pstmt.setString(2, bean.getAddvnm()); // 第二个？号的内容
			pstmt.addBatch();
		}

		pstmt.executeBatch();// 执行SQL 语句，更新数据库
		con.commit();
		pstmt.close();
		con.close(); // 4、关闭数据库
	}

	public static void add(AddvcdBean bean) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection con = null; // 表示数据库的连接对象
		PreparedStatement pstmt = null; // 表示数据库更新操作

		String sql = "insert into t_addvcd_all values(?,?)";

		Class.forName(DBDRIVER); // 1、使用CLASS 类加载驱动程序

		System.out.println(sql);
		con = DriverManager.getConnection(DBURL, DBUSER, DBPASS); // 2、连接数据库
		con.setAutoCommit(false);

		pstmt = con.prepareStatement(sql); // 使用预处理的方式创建对象

		pstmt.setString(1, bean.getAddvcd()); // 第一个？号的内容
		pstmt.setString(2, bean.getAddvnm()); // 第二个？号的内容
		pstmt.addBatch();
		pstmt.execute(); // 执行SQL 语句，更新数据库
		con.commit();
		pstmt.close();
		con.close(); // 4、关闭数据库
	}
}
