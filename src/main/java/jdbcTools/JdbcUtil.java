/**
 * 
 */
package jdbcTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CHEN XINGXING
 * @Description: jdbc工具类
 */
public class JdbcUtil {
	/**
	 * 
	 * 得到连接
	 * @param driverClass
	 *            驱动
	 * @param url
	 * @param userName
	 * @param passWord
	 * @return 连接
	 */
	public static Connection getConnection(String driverClass, String url,
			String userName, String passWord) {
		Connection connection = null;
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, userName, passWord);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 执行查询
	 * @param connection
	 *            连接
	 * @param sql
	 * @return 结果集
	 */
	public static ResultSet executeQuery(Connection connection, String sql) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseConnection(rs, statement, connection);
		}
		return rs;
	}

	/**
	 * 
	 * 释放数据库连接
	 * @param rs
	 * @param statement
	 * @param connection
	 */
	public static void releaseConnection(ResultSet rs,
			PreparedStatement statement, Connection connection) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			if (connection != null && (!connection.isClosed())) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
