package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.VO;

public class OnelistCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {

		   // 테이블 전체 정보 가져오기
		   	String idx = request.getParameter("idx");
		    VO vo = DAO.getInstance().getSelectOne(idx);
		    request.setAttribute("vo", vo);
		
		return "view/onelist.jsp";
	}
}
