package com.escape.controller.qboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.escape.controller.SuperClass;
import com.escape.dao.QBoardDao;
import com.escape.model.QBoard;

public class QBoardReplyController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		 
		int qgroupno = Integer.parseInt(request.getParameter("qgroupno"));
	      int qorderno = Integer.parseInt(request.getParameter("qorderno"));
	      int qdepth = Integer.parseInt(request.getParameter("qdepth"));
	      QBoard bean = new QBoard();
	      
	      bean.setQgroupno(qgroupno); //넘어온 그룹 번호는 동일하게
	      bean.setQorderno(qorderno); // 정렬 순서는 +1
	      bean.setQdepth(qdepth); // 글의 깊이도 +1
	      
	      request.setAttribute("bean", bean);

		
			super.GotoPage("qboard/qboReplyForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		
				String qwriter = request.getParameter("qwriter");
				String qsubject = request.getParameter("qsubject");
				String qcontent = request.getParameter("qcontent");
				
				//다음은 답글을 위하여 반드시 챙겨야 하는 파라미터입니다.
				int qgroupno = Integer.parseInt(request.getParameter("qgroupno"));
				int qorderno = Integer.parseInt(request.getParameter("qorderno"));
				int qdepth = Integer.parseInt(request.getParameter("qdepth"));
				
				QBoard bean = new QBoard();
				
				bean.setQwriter(qwriter);
				bean.setQsubject(qsubject);
				bean.setQcontent(qcontent);
				
				bean.setQgroupno(qgroupno); //넘어온 그룹 번호는 동일하게
				bean.setQorderno(qorderno + 1); // 정렬 순서는 +1
				bean.setQdepth(qdepth + 1); // 글의 깊이도 +1
				
				int cnt = -1;
				QBoardDao dao = new QBoardDao();
				try {
					cnt = dao.ReplyData(bean, qorderno);
					new QBoardListController().doGet(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
		}		
	}

}
