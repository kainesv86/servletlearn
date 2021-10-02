/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import moddel.User;

/**
 *
 * @author Kaine
 */
public class UserDAO {

	private Connection conn;
	private PreparedStatement preStm;
	private ResultSet rs;

	public void closeConnection() throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (preStm != null) {
			preStm.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public User getUserByName(String fullName) throws Exception {
		try {
			conn = Connector.getConnection();
			String sql = "SELECT * FROM tbl_user WHERE fullName = ?";

			preStm = conn.prepareStatement(sql);
			preStm.setString(1, fullName);
			rs = preStm.executeQuery();
			while (rs.next()) {
				String userId = rs.getString("userId");
				Integer password = rs.getInt("password");
				Integer role = rs.getInt("role");

				return new User(userId, fullName, password, role);
			}
		} finally {
			this.closeConnection();
		}
		return null;
	}

	public ArrayList<User> getAllUser() throws Exception {
		try {
			conn = Connector.getConnection();
			String sql = "SELECT * FROM tbl_user";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			ArrayList<User> list = new ArrayList<User>();
			while (rs.next()) {
				String userId = rs.getString("userId");
//				Integer password = rs.getInt("password");
				String fullName = rs.getString("fullName");
				Integer role = rs.getInt("role");
				list.add(new User(userId, fullName, null, role));
			}
			return list;
		} finally {
			this.closeConnection();
		}
	}

	public boolean insertUserToDb(User user) throws Exception {
		try {
			conn = Connector.getConnection();
			String sql = "INSERT INTO tbl_User(userId, password, fullName, role) VALUES(?, ?, ? ,?)";

			preStm = conn.prepareStatement(sql);
			preStm.setString(1, user.getUserId());
			preStm.setInt(2, user.getPassword());
			preStm.setString(3, user.getFullName());
			preStm.setInt(4, user.getRole());

			preStm.executeUpdate();
		} catch (Exception e) {
			return false;
		} finally {
			this.closeConnection();
		}
		return true;
	}

	public boolean updatePasswordByName(User user, Integer newPassword) throws Exception {
		try {
			conn = Connector.getConnection();
			String sql = "UPDATE tbl_User set password=? where fullName=?";

			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, newPassword);
			preStm.setString(2, user.getFullName());
			preStm.executeUpdate();
			System.out.println("Work pls");
		} catch (Exception e) {
			return false;
		} finally {
			this.closeConnection();
		}
		return true;
	}

	public boolean updateInfoFromAdmin(String fullName, Integer newPassword, Integer role) throws Exception {
		try {
			conn = Connector.getConnection();
			String sql = "UPDATE tbl_User set password=?, role=?   where fullName=?";

			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, newPassword);
			preStm.setInt(2, role);
			preStm.setString(3, fullName);
			preStm.executeUpdate();
		} catch (Exception e) {
			return false;
		} finally {
			this.closeConnection();
		}
		return true;
	}
}
