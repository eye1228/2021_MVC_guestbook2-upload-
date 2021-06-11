package com.ict.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ict.db.DAO;
import com.ict.db.VO;

public class Write_okCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(2);
		String path = request.getSession().getServletContext().getRealPath("/upload");
		
		try {
			
		//���� ���� ����
		MultipartRequest mr = 
				  new MultipartRequest(request,path,100*1024*1024,"utf-8",new DefaultFileRenamePolicy());
	
			VO vo = new VO();
			
			System.out.println(mr.getParameter("name"));
			System.out.println(mr.getParameter("title"));
			System.out.println(mr.getParameter("email"));
			System.out.println(mr.getParameter("pw"));
			System.out.println(mr.getParameter("msg"));
			System.out.println(mr.getParameter("f_name"));
			
			
			vo.setName(mr.getParameter("name"));
			vo.setTitle(mr.getParameter("title"));
			vo.setEmail(mr.getParameter("email"));
			vo.setPw(mr.getParameter("pw"));
			vo.setMsg(mr.getParameter("msg"));
			if(mr.getFile("f_name") != null){//÷�� ������ �ִ�.
				vo.setF_name(mr.getFilesystemName("f_name"));
			}else{//����.
				vo.setF_name("");//���߿� ������ �� �����Ƿ� �����ϴ� ������ �鿩����.
			}
			
			int result = DAO.getInstance().getInsert(vo);
			if(result > 0) {
				//response.sendRedirect("list.jsp");
				return "MyController?cmd=list";
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			return null;
		}
	
}
