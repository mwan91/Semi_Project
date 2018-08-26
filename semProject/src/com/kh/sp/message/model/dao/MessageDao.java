package com.kh.sp.message.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.sp.member.model.dao.MemberDao;
import com.kh.sp.message.model.vo.Message;

import static com.kh.sp.common.JDBCTemplate.*;

public class MessageDao {
	private Properties prop = new Properties();

	public MessageDao(){
		String fileName = MessageDao.class.getResource("/sql/message/message-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int sendMessage(Connection con, Message m) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("sendMessage");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, 999);
			pstmt.setInt(2, m.getUser_id());
			pstmt.setString(3, m.getTitle());
			pstmt.setString(4, m.getMsg());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		
		return result;
	}
	public Message readMessage(Connection con, int msgId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Message m = null;
		
		String query = prop.getProperty("readMessage");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, msgId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				m = new Message();
				
				m.setMsg_id(rset.getInt("msg_id"));
				m.setTitle(rset.getString("title"));
				m.setMsg(rset.getString("content"));
				m.setUser_id(rset.getInt("send_id"));
				m.setReceive_id(rset.getInt("receive_id"));
				m.setSend_date(rset.getDate("send_date"));
				m.setReadYN(rset.getString("readyn"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}
		
		
		
		return m;
	}

}