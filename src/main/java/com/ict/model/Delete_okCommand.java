package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;

public class Delete_okCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {

		 String idx = request.getParameter("idx");
		  int result = DAO.getInstance().getDelete(idx);
		  
			if(result>0){ 
			  	//여기서 실제 그림파일 지우기 하면 됨
				return "MyController?cmd=list";
			}
		return null;
	}
}
