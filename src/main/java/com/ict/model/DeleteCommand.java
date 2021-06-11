package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");
		String pw = request.getParameter("pw");
		
		request.setAttribute("idx", idx);
		request.setAttribute("pw", pw);
		
		
		
		return "view/del.jsp";
	}
}
