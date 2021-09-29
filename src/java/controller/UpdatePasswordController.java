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
@WebServlet(name = "UpdatePasswordController", urlPatterns = {"/UpdatePasswordController"})
public class UpdatePasswordController extends HttpServlet {

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
		throws Exception {
		UserDAO userDao = new UserDAO();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user.getFullName() == null) {
			response.sendRedirect(Routers.LOGIN_CONTROLLER);
		}

		Integer passwordUser;
		try {
			passwordUser = userDao.getUserByName(user.getFullName()).getPassword();
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Something got error from passwordUser");
			return false;
		}

		Integer password, newPassword, confirmPassword;
		try {
			password = GetParam.getIntParams(request, "password", "Password", 0, Integer.MAX_VALUE, null);
			newPassword = GetParam.getIntParams(request, "newPassword", "new password", 0, Integer.MAX_VALUE, null);
			confirmPassword = GetParam.getIntParams(request, "confirmPassword", "confirm password", 0, Integer.MAX_VALUE, null);
		} catch (Exception e) {
			password = null;
			newPassword = null;
			confirmPassword = null;
		}

		if (password == null || !Objects.equals(password, passwordUser)) {
			System.out.println(Objects.equals(password, passwordUser));
			request.setAttribute("passwordError", "Password error is not correct");
			return false;
		}

		if (!Objects.equals(newPassword, confirmPassword) || confirmPassword == null || newPassword == null) {
			request.setAttribute("confirmPassword", "Confirm password is not correct");
			return false;
		}

		if (!userDao.updatePasswordByName(user, newPassword)) {
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
		request.getRequestDispatcher(Routers.UPDATE_PASSWORD_PAGE).forward(request, response);
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
				response.sendRedirect(Routers.INDEX_CONTROLLER);
				System.out.println("Change password successful");
//				request.getRequestDispatcher(Routers.INDEX_PAGE).forward(request, response);
				return;
			}
			request.getRequestDispatcher(Routers.UPDATE_PASSWORD_PAGE).forward(request, response);
		} catch (Exception e) {
			System.out.println("Something got error");
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
