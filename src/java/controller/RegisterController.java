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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import moddel.User;

/**
 *
 * @author Kaine
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

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

		String userId = GetParam.getStringParam(request, "userId", "User Id", 0, 30, "");
		String fullName = GetParam.getStringParam(request, "fullName", "FullName", 0, 30, "");
		Integer password;
		Integer role;
		try {
			password = GetParam.getIntParams(request, "password", "password", 0, Integer.MAX_VALUE, null);
			role = GetParam.getIntParams(request, "role", "Role", 0, 3, null);
		} catch (Exception e) {
			return false;
		}

		if (userId == null || fullName == null || password == null || role == null) {
			return false;
		}

		User user = new User(userId, fullName, password, role);
		if (!userDao.insertUserToDb(user)) {
			request.setAttribute("errorMessage", "Something got error in database!");
			return false;
		}
		System.out.println("Hello");
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
		request.getRequestDispatcher(Routers.REGISTER_PAGE).forward(request, response);
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
//				response.sendRedirect(Routers.INDEX_CONTROLLER);
				request.getRequestDispatcher(Routers.INDEX_PAGE).forward(request, response);
				return;
			}
			request.getRequestDispatcher(Routers.REGISTER_PAGE).forward(request, response);
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
