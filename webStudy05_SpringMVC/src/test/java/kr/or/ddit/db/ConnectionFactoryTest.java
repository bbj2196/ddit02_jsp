package kr.or.ddit.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionFactoryTest {

	@Test
	public void testGetConnection() throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		assertNotNull(conn);
	}

}
