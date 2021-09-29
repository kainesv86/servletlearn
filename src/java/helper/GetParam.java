/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kaine
 */
public class GetParam {

	public static String getStringParam(HttpServletRequest request, String field, String label, int min, int max,
		String defaultValue) {
		String value = (String) request.getParameter(field);

		if (value == null || value.trim().isEmpty()) {
			if (defaultValue == null) {
				request.setAttribute(field + "Error", label + " is required");
				return null;
			}

			return defaultValue;
		}
		if (value.trim().length() > max) {
			request.setAttribute(field + "Error", label + " is less than or equal " + max + " character(s)");
			return null;
		}
		if (value.trim().length() < min) {
			request.setAttribute(field + "Error", label + " is greater than or equal " + min + " character(s)");
			return null;
		}
		return value.trim();
	}

	/**
	 * Get integer from request parameter and validate it, if it invalid,
	 * return default value
	 *
	 * @param request servlet request
	 * @param field request parameter name
	 * @param label Label
	 * @param min minimum number
	 * @param max maximum number
	 * @return Valid integer
	 */
	public static Integer getIntParams(HttpServletRequest request, String field, String label, int min, int max,
		Integer defaultValue) {

		String value = (String) request.getParameter(field);
		Integer realValue;

		if (value == null || value.isEmpty()) {
			if (defaultValue == null) {
				request.setAttribute(field + "Error", label + " is required");
				return null;
			}
			return defaultValue;
		}
		try {
			realValue = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			request.setAttribute(field + "Error",
				label + " must be a number and less than or equal " + Integer.MAX_VALUE);
			return null;
		}
		if (realValue > max) {
			request.setAttribute(field + "Error", label + " is less than or equal " + max);
			return null;
		}
		if (realValue < min) {
			request.setAttribute(field + "Error", label + " is greater than or equal " + min);
			return null;
		}

		return realValue;
	}

	/**
	 * Get float from request parameter and validate it, if it invalid,
	 * return default value
	 *
	 * @param request servlet request
	 * @param field request parameter name
	 * @param label Label
	 * @param min minimum number
	 * @param max maximum number
	 * @return Valid float
	 */
	public static Float getFloatParams(HttpServletRequest request, String field, String label, float min, float max,
		Float defaultValue) {

		String value = (String) request.getParameter(field);
		Float realValue;
		if (value == null || value.isEmpty()) {
			if (defaultValue == null) {
				request.setAttribute(field + "Error", label + " is required");
				return null;
			}
			return defaultValue;
		}

		try {
			realValue = Float.parseFloat(value);
		} catch (NumberFormatException e) {

			request.setAttribute(field + "Error",
				label + " must be a number and less than or equal " + Float.MAX_VALUE);
			return null;
		}
		if (realValue > max) {
			request.setAttribute(field + "Error", label + " is less than or equal " + max);
			return null;
		}
		if (realValue < min) {
			request.setAttribute(field + "Error", label + " is greater than or equal " + min);
			return null;
		}

		return realValue;
	}

}
