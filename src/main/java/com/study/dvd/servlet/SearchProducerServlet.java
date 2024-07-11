package com.study.dvd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/producer/search")
public class SearchProducerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*
	 *	1. 요청 URL = /producer/search
	 *	2. dao.ProducerDao   => searchProducerByProducerName()
	 *  3. entity.Producer
	 * 	4. search_producer.jsp
	 * 
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/search_producer.jsp").forward(req, resp);
	// System.out.println("요청들어옴? ");
	// 요청이 들어옴
	}
	// tomcat 웹서버(8080)
	// 요청들어오면 req , resp를 생성 찾아갈 주소가 없으면 404 , 있으면 200으로 이동 
	// doGet이 있으면 req, resp를 사용
	// doGet의 호출이 끝나면 tomcat으로 돌아감
}
