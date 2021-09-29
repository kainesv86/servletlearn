/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kaine
 */
public class Connector {

	public static Connection getConnection() {
		Connection connection = null;
		String IP = "localhost";
		String instanceName = "MSSQLSERVER01";
		String port = "1433";
		String db = "testing";
		String username = "sa";
		String password = "123456789";

		String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port + ";databaseName=" + db + ";user=" + username + ";password=" + password;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url);
			System.out.println("Connection to database successfull");
		} catch (SQLException error) {
			error.printStackTrace();
		} catch (ClassNotFoundException error) {
			error.printStackTrace();
		}

		return connection;
	}
}
