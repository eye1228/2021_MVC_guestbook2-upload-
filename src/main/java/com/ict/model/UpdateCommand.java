package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class UpdateCommand implements Command{
	public String exec(HttpServletRequest request, HttpServletResponse response)
	{
		String idx = request.getParameter("idx");
		VO vo = DAO.getInstance().getSelectOne(idx);
		 
		request.setAttribute("vo", vo);
	
		return "view/update.jsp";
		
	}
}
