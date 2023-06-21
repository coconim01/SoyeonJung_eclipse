package com.escape.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;


public class MemberLogoutController extends SuperClass {
	@Override // 회원이 로그 아웃을 시도합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 로그인시 세션에 저장했던 로그인 정보, 장바구니 정보 등등을 삭제(휘발)하고, 로그인 페이지로 이동합니다. 
		super.session.invalidate();
		super.GotoPage("member/meLoginForm.jsp"); 
	}
}










