package com.kh.sp.myPage.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.sp.board.model.vo.Attachment;
import com.kh.sp.member.model.vo.Member;
import com.kh.sp.myPage.model.dao.MypageDetailDao;
import com.kh.sp.myPage.model.vo.MypageDetail;
import static com.kh.sp.common.JDBCTemplate.*;

public class MypageDetailService {

	public ArrayList<MypageDetail> selectList(int userId) {
		ArrayList<MypageDetail> list = new ArrayList<MypageDetail>();
		
		Connection con = getConnection();
		
		list = new MypageDetailDao().selectList(con, userId);
		
		close(con);
		
		return list;
	}

	public int insertAttachment(ArrayList<Attachment> fileList, int userid, int ptype) {
		Connection con = getConnection();
		
		int result = new MypageDetailDao().insertAttachment(con, fileList, userid, ptype);
		
		if(result > 0) {
			commit(con);
		}
		else {
			rollback(con);
		}
		close(con);
		return result;
	}

}
