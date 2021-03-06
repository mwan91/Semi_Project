package com.kh.sp.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.sp.admin.model.service.AdminService;
import com.kh.sp.admin.model.vo.DetailMember;
import com.kh.sp.admin.model.vo.PageInfo;

@WebServlet("/confirmSelectAll.adm")
public class selectAllConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public selectAllConfirmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("셀렉트올 컨펌 프로젝트 서블릿 올까?");
	int currentPage;
	int limit;
	int maxPage;
	int startPage;
	int endPage;

currentPage = 1;
	
	if(request.getParameter("currentPage") != null){
		currentPage
		= Integer.parseInt(request.getParameter("currentPage"));
		
	}
	
	
	int listCount = new AdminService().getConfirmListCount();
	
	limit = 10;
	
	//총 페이지 수 계산
	maxPage = (int)((double)listCount / 10 + 0.9);
	
	startPage = (((int)((double)currentPage / 10 + 0.9)) -1) * 10 + 1;
	
	endPage = startPage + 10 -1;
	
	if(maxPage < endPage){
		endPage = maxPage;
	}
	
	PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
	
	ArrayList<DetailMember> confirmList = new AdminService().selectAllConfirm(currentPage, limit);
	
	System.out.println("pi :" + pi);
	System.out.println("dlineList : " + confirmList);
	
	String page = "";
	
	if(confirmList != null){
		System.out.println("마감 해줘야하는 사람들~");
		page = "views/admin/confirmProject.jsp";
		request.setAttribute("confirmList", confirmList);
		request.setAttribute("pi", pi);
	}else{
		page="views/common/errorPage.jsp";
		request.setAttribute("msg", "심사관리 조회 실패");
	}
	RequestDispatcher view = request.getRequestDispatcher(page);
	view.forward(request, response);
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
