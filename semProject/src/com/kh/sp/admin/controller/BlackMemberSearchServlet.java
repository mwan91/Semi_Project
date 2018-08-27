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
import com.kh.sp.admin.model.vo.PageInfo;
import com.kh.sp.member.model.vo.Member;

@WebServlet("/blackSearch.adm")
public class BlackMemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BlackMemberSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;
		
		System.out.println("블랙서치 오닝??");
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null){
			currentPage
			= Integer.parseInt(request.getParameter("currentPage"));
			
		}
		
		System.out.println("여기까지됨 : " + currentPage);
		
		String text = request.getParameter("searchValue");
		
		System.out.println("text 는 = " + text);
		
		int listCount = new AdminService().blackListCount(text);
		
		limit = 10;
		
		//총 페이지 수 계산
		maxPage = (int)((double)listCount / 10 + 0.9);
		
		startPage = (((int)((double)currentPage / 10 + 0.9)) -1) * 10 + 1;
		
		endPage = startPage + 10 -1;
		
		if(maxPage < endPage){
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		
		ArrayList<Member> blackList = null;
		
		

			blackList = new AdminService().searchBlackMember(currentPage, limit, text);

	
		String page ="/views/admin/blackSearchPage.jsp";
		
		System.out.println("마지막 list는" + blackList);
		if(blackList != null || blackList.size() > 0){
			//이거 조심하자 저번에도 실수했는데 또 ㅠㅠ
			request.setAttribute("blackList", blackList);
			request.setAttribute("pi", pi);
		}else{
			request.setAttribute("msg", "검색 결과가 없습니다");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
