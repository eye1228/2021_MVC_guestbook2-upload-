package com.ict.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//상수와 추상 메소드만 가지고 있다 == 인터페이스
public interface Command {
	public String exec(HttpServletRequest request, HttpServletResponse response);
}
