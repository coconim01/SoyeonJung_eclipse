package com.escape.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//HomeController = ProductThemaListController
public class HomeController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//메인페이지로 이동
		super.doGet(request, response);
		
		super.GotoPage("home.jsp");
	}
}
