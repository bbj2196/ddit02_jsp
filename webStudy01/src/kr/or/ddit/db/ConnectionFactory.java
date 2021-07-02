package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * Factory Object[Method] Pattern
 * @author PC-13
 *
 */
public class ConnectionFactory {

	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	private static String drivername;
	private static int initialSize;
	private static int maxTotal;
	private static int maxIdle;
	private static long maxWait;
	static{
		Properties dbProps = new Properties();
		InputStream is =ConnectionFactory.class.getResourceAsStream("/kr/or/ddit/db/dbinfo.properties");
		try {
			dbProps.load(is);
//			Class.forName(dbProps.getProperty("driverClassName"));
			url = dbProps.getProperty("url");
			user=dbProps.getProperty("user");
			password=dbProps.getProperty("password");
			drivername = dbProps.getProperty("driverClassName");
			initialSize=Integer.parseInt(dbProps.getProperty("initialSize"));
			maxTotal=Integer.parseInt(dbProps.getProperty("maxTotal"));   
			maxIdle= Integer.parseInt(dbProps.getProperty("maxIdle"));    
			maxWait = Long.parseLong(dbProps.getProperty("maxWait"));   
//			OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();
//			ds.setURL(url);
//			ds.setUser(user);
//			ds.setPassword(password);
			
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName(drivername);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
			ds.setInitialSize(initialSize);
			ds.setMaxWaitMillis(maxWait);
			ds.setMaxTotal(maxTotal);
			ds.setMaxIdle(maxIdle);
			
			dataSource=ds;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static Connection getConnection() throws SQLException {
		// 2. 드라이버 클래스 로딩
				
				// 3. Connection 객체 생성
//				Connection conn=DriverManager.getConnection(url, user, password);
				Connection conn = dataSource.getConnection();
				return conn;
	}
}
