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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
//
		String fullName = GetParam.getStringParam(request, "fullName", "FullName", 0, 30, "");
		Integer password;

		try {
			password = GetParam.getIntParams(request, "password", "Password", 8, Integer.MAX_VALUE, null);
		} catch (Exception e) {
			password = null;
		}

		User user = userDao.getUserByName(fullName);
		if (user == null) {
			request.setAttribute("fullNameError", "FullName is not correct");

			return false;
		}
		if (!Objects.equals(user.getPassword(), password)) {
			request.setAttribute("passwordError", "Password is not correct");
			return false;
		}

		user.setPassword(null);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
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
		request.getRequestDispatcher(Routers.LOGIN_PAGE).forward(request, response);
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
//				request.getRequestDispatcher(Routers.INDEX_PAGE).forward(request, response);
				return;
			}
			request.getRequestDispatcher(Routers.LOGIN_PAGE).forward(request, response);
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
