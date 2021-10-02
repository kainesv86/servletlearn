/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constants.Routers;
import dao.UserDAO;
import helper.GetParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import moddel.User;

/**
 *
 * @author Kaine
 */
@WebServlet(name = "UpdateUserInfoController", urlPatterns = {"/UpdateUserInfoController"})
public class UpdateUserInfoController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, Exception {
		UserDAO userDao = new UserDAO();
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");
//		if (user.getRole() != 1) {
//			response.sendRedirect(Routers.INDEX_CONTROLLER);
//			return false;
//		}

//		String fullName = GetParam.getStringParam(request, "fullName", "Fullname", 0, 0, null);
//		request.setAttribute("fullName", fullName);
//		System.out.println(fullName);
		Integer role, newPassword, confirmPassword;
		String fullName = null;
		try {
			fullName = GetParam.getStringParam(request, "fullName", "Fullname", 0, 30, null);
			newPassword = GetParam.getIntParams(request, "newPassword", "new password", 0, Integer.MAX_VALUE, null);
			confirmPassword = GetParam.getIntParams(request, "confirmPassword", "confirm password", 0, Integer.MAX_VALUE, null);
			role = GetParam.getIntParams(request, "role", "role", 0, Integer.MAX_VALUE, null);
		} catch (Exception e) {
			newPassword = null;
			confirmPassword = null;
			role = null;
		}

		if (fullName != null) {
			User userInfo = userDao.getUserByName(fullName);
			request.setAttribute("userInfo", userInfo);
		}

		if (newPassword == null) {
			request.setAttribute("newPassword", "newPassword is not correct");
			return false;
		}

		if (!Objects.equals(newPassword, confirmPassword) || confirmPassword == null) {
			request.setAttribute("confirmPassword", "Confirm password is not correct");
			return false;
		}

		if (role == null) {
			request.setAttribute("role", "Role is not empty");
			return false;
		}

		if (!userDao.updateInfoFromAdmin(fullName, newPassword, role)) {
			request.setAttribute("errorMessage", "Something got error database");
			return false;
		}
		return true;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String fullName = GetParam.getStringParam(request, "fullName", "Fullname", 0, 100, null);

		UserDAO userDao = new UserDAO();
		try {
			User userInfo = userDao.getUserByName(fullName);
			System.out.println("userInfo: " + userInfo.getFullName());
			request.setAttribute("userInfo", userInfo);
		} catch (Exception e) {
		}

//
//
		request.getRequestDispatcher(Routers.UPDATE_USER_FROM_ADMIN_PAGE).forward(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			if (processRequest(request, response)) {
				response.sendRedirect(Routers.ADMIN_CONTROLLER);
				return;
			}
//			System.out.println("UserInfo:" + request.getAttribute("userInfo"));
			request.getRequestDispatcher(Routers.UPDATE_USER_FROM_ADMIN_PAGE).forward(request, response);
		} catch (Exception e) {
			System.out.println("Something got error 1");
		}
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
