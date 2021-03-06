package com.kh.sp.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.sp.board.model.vo.Board;
import com.kh.sp.funding.model.service.FundingService;
import com.kh.sp.funding.model.vo.Product;
import com.kh.sp.main.model.service.MainService;
import com.kh.sp.member.model.service.MemberService;
import com.kh.sp.member.model.vo.Member;

@WebServlet("/snsLogin.na")
public class SNSLoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SNSLoginServlet2() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userEmail = request.getParameter("email");
		String nickName = request.getParameter("nickname");
		String accessToken = request.getParameter("accessToken");
		String refreshToken = request.getParameter("refreshToken");
		String platformId = request.getParameter("id");
		
		Member m = new Member();
		m.setEmail(userEmail);
		m.setPlatformType("naver");
		m.setNickName(nickName);
		m.setRefreshToken(refreshToken);
		m.setRefreshToken(refreshToken);
		m.setPlatformId(platformId);
		
		Member loginUser = new MemberService().checkEmailMember(userEmail);
		
		if(loginUser == null){
			loginUser = new MemberService().checkMember(m);
		}else{
			request.setAttribute("msg", "이미 이메일로 가입된 계정입니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/member/loginForm.jsp");
			view.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		
		if(loginUser == null){
			int result = new MemberService().insertSnsMember(m);
			
			if(result > 0){
				request.setAttribute("msg", "회원 가입이 완료되었습니다.");
				session.setAttribute("accessToken", accessToken);
			}
			else{
				request.setAttribute("msg", "회원 가입에 실패하였습니다.");
			}
			RequestDispatcher view = request.getRequestDispatcher("views/member/loginForm.jsp");
			view.forward(request, response);
			
		}else{
			//로그인 성공
			session.setAttribute("accessToken", accessToken);
			session.setAttribute("loginUser", loginUser);
			
			ArrayList<Board> list = new MainService().startMain();
			ArrayList<Product> newFList = new FundingService().newFundingList();
			ArrayList<Product> mainFList = new FundingService().mainFundingList();
			ArrayList<Product> hotFList = new FundingService().hotFundingList();
			ArrayList<Product> closeFList = new FundingService().closeFundingList();
			
			request.setAttribute("list", list);
			request.setAttribute("newFList", newFList);
			request.setAttribute("mainFList", mainFList);
			request.setAttribute("hotFList", hotFList);
			request.setAttribute("closeFList", closeFList);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
			/*RequestDispatcher view = request.getRequestDispatcher("views/main/main.jsp");
			view.forward(request, response);*/
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
