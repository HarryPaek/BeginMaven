/**
 * 
 */
package net.foundation.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

/**
 * @author HarryPaek
 *
 */
public class MySQLConnectionTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/book_ex?useSSL=false&serverTimezone=UTC";
	private static final String USER = "foundation";
	private static final String PW = "foundation";

	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);
		try(Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println(con);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
