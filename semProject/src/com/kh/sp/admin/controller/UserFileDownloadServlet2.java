package com.kh.sp.admin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.sp.admin.model.service.AdminService;
import com.kh.sp.board.model.vo.Attachment;

@WebServlet("/uFileDown2")
public class UserFileDownloadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserFileDownloadServlet2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("등급서류 다운로드 서블릿2 ");

		int num = Integer.parseInt(request.getParameter("user_id3"));
		System.out.println("num =" + num);
		Attachment file = new AdminService().userDownloadFileAttachment(num);
		
		//폴더에서 파일을 읽을 스트림 생성
		BufferedInputStream buf = null;
		
		//클라이언트에게 내보낼 출력 스트림 생성
		ServletOutputStream downOut = null;
		
		downOut = response.getOutputStream();
		
		File downFile = new File(file.getFilePath() + file.getChangeName());
		
		response.setContentType("text/plain; charset=utf-8");
		
		//한글과 파일명에 대해서 인코딩 하는 부분
		//다운로드를 강제적으로 처리
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(file.getOriginName().getBytes("UTF-8"), "ISO-8859-1") + "\"");
		
		response.setContentLength((int)downFile.length());
		
		FileInputStream fin = new FileInputStream(downFile);
		
		buf = new BufferedInputStream(fin);
		
		int readBytes = 0;
		while((readBytes = buf.read()) != -1){
			downOut.write(readBytes);
		}
		downOut.close();
		buf.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
