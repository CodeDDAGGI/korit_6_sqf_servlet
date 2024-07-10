package com.study.dvd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	String name = "김준일";
//    	
//    	resp.setContentType("text/html");
//    	resp.setCharacterEncoding("utf-8");
//    	// plain - 문자열 그대로 출력
//    	// html
//    	resp.getWriter().println(""
//    			+ "<html>"
//    			+ "<head>"
//    			+ "<title>hello</title>"
//    			+ "</head>"
//    			+ "<body>"
//    			+ "<h1>Hello Servlet!!!</h1>"
//    			+ "<h2>" + name + "</h2>"
//    			+ "</body>"
//    			+ "</html>");
    	
		// 서블릿(내부에 들어있음)에 요청 - 캡슐화
    	@Override // 파라미터는 무조건 문자열 작성
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		String name = req.getParameter("name");
    	    String age = req.getParameter("age");
    		
    	    System.out.println(name);
    	    System.out.println(age);
    	    
    		req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req, resp);
    }
}
    
    

