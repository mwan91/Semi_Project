package com.kh.sp.admin.model.dao;

import static com.kh.sp.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.sp.admin.model.vo.CheckProject;
import com.kh.sp.admin.model.vo.DetailMember;
import com.kh.sp.admin.model.vo.MemberStatistics;
import com.kh.sp.admin.model.vo.OpenFundingStatistics;
import com.kh.sp.admin.model.vo.SalesStatistics;
import com.kh.sp.admin.model.vo.SuccessFundingStatistics;
import com.kh.sp.board.model.vo.Attachment;
import com.kh.sp.member.model.vo.Member;

public class AdminDao {
	private Properties prop = new Properties();

	public AdminDao(){
		String fileName = AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

	public int getListCount(Connection con) {
		Statement stmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("listCount");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				listCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(stmt);
			close(rset);

		}


		return listCount;
	}
	public int getBlackListCount(Connection con) {
		Statement stmt = null;
		int blackListCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("blackListCount");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				blackListCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(stmt);
			close(rset);

		}


		return blackListCount;
	}


	//회원 전체 조회 메소드
	public ArrayList<Member> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Member> list = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectAllMember");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;

		
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			list = new ArrayList<Member>();

			while(rset.next()){
				Member m = new Member();

				m.setUserId(rset.getInt("user_Id"));
				m.setUserClass(rset.getString("user_Class"));
				m.setUserName(rset.getString("user_Name"));
				m.setNickName(rset.getString("nick_Name"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));

				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}


		return list;
	}

	public ArrayList<Member> searchAllMember(Connection con, int currentPage, int limit, String text) {
		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("searchAllMember");
		System.out.println("제발 여기까진 와주라");
		try {
			pstmt= con.prepareStatement(query);

			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setString(4, text);
			pstmt.setString(5, text);
			pstmt.setString(6, text);
			pstmt.setInt(7, startRow);
			pstmt.setInt(8, endRow);

			list = new ArrayList<Member>();
			rset = pstmt.executeQuery();
			while(rset.next()){
				Member m = new Member();

				m.setUserId(rset.getInt("user_Id"));
				m.setUserClass(rset.getString("user_Class"));
				m.setUserName(rset.getString("user_Name"));
				m.setNickName(rset.getString("nick_Name"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));

				list.add(m);
			};

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}
		System.out.println("리스트는~" + list);

		return list;
	}
	public ArrayList<Member> sortMember(Connection con, int currentPage, int limit, String sort) {

		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query1 = prop.getProperty("sortUser_id");
		String query2 = prop.getProperty("sortUser_class");
		String query3 = prop.getProperty("sortUser_name");
		String query4 = prop.getProperty("sortNick_name");
		String query5 = prop.getProperty("sortEmail");
		String query6 = prop.getProperty("sortPhone");
		
		System.out.println(sort);

		try {

			if(sort.equals("user_id")){
				pstmt = con.prepareStatement(query1);
			}else if(sort.equals("user_class")){
				pstmt = con.prepareStatement(query2);
			}else if(sort.equals("user_name")){
				pstmt = con.prepareStatement(query3);
			}else if(sort.equals("nickname")){
				pstmt = con.prepareStatement(query4);
			}else if(sort.equals("email")){
				pstmt = con.prepareStatement(query5);
			}else if(sort.equals("phone")){
				pstmt = con.prepareStatement(query6);
			}
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			list = new ArrayList<Member>();

			while(rset.next()){

				Member m = new Member();

				m.setUserId(rset.getInt("user_Id"));
				m.setUserClass(rset.getString("user_Class"));
				m.setUserName(rset.getString("user_Name"));
				m.setNickName(rset.getString("nick_Name"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));

				list.add(m);
			};


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}


		System.out.println("list 가져왔어요" + list);
		return list;

	}



	public Member selectOne(Connection con, int user_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Member m = null;

		String query = prop.getProperty("selectOneMember");

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, user_id);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				m = new Member();

				m.setUserId(rset.getInt("user_id"));
				m.setUserClass(rset.getString("user_class"));
				m.setUserName(rset.getString("user_name"));
				m.setNickName(rset.getString("nick_name"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setEnrollDate(rset.getDate("enroll_date"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		System.out.println("객체 m 은 ? = " + m);
		return m;
	}

	public ArrayList<Member> selectBlackList(Connection con, int currentPage, int limit) {
		ArrayList<Member> blackList = null;

		System.out.println("이게 안나오면 이상하지");
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		System.out.println("여기 오나 ?");
		String query = prop.getProperty("blackSelectAll");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			blackList = new ArrayList<Member>();

			while(rset.next()){
				Member m = new Member();

				m.setUserId(rset.getInt("user_Id"));
				m.setUserClass(rset.getString("user_Class"));
				m.setUserName(rset.getString("user_Name"));
				m.setCorporateName(rset.getString("corporate_Name"));
				m.setB_reason(rset.getString("reason"));
				m.setB_enrollDate(rset.getDate("enroll_Date"));

				blackList.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}

		System.out.println(blackList);
		return blackList;
	}

	public int searchMemberListCount (Connection con, String text) {
		PreparedStatement pstmt = null;
		int searchList = 0;
		ResultSet rset = null;

		String query = prop.getProperty("searchMemberListCount");

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setString(4, text);
			pstmt.setString(5, text);
			pstmt.setString(6, text);

			rset = pstmt.executeQuery();

			if(rset.next()) {
				searchList = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);

		}


		return searchList;
	}


	public int insertBlackList(Connection con, String blackText, int userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBlackList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userId);
			pstmt.setString(2, blackText);
			
			result = pstmt.executeUpdate();
		
			 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Member> searchBlackMember(Connection con, int currentPage, int limit, String text) {
		ArrayList<Member> blackList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("blackMemberSearch");

		try {
			pstmt = con.prepareStatement(query);

			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit - 1;

			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setString(4, text);
			pstmt.setString(5, text);
			pstmt.setString(6, text);
			pstmt.setInt(7, startRow);
			pstmt.setInt(8, endRow);

			blackList = new ArrayList<Member>();
			rset = pstmt.executeQuery();
			while(rset.next()){
				Member m = new Member();

				m.setUserId(rset.getInt("user_Id"));
				m.setUserClass(rset.getString("user_Class"));
				m.setUserName(rset.getString("user_Name"));
				m.setCorporateName(rset.getString("corporate_Name"));
				m.setB_reason(rset.getString("reason"));
				m.setB_enrollDate(rset.getDate("enroll_Date"));

				blackList.add(m);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}


		return blackList;
	}

	public int blackListCount(Connection con, String text) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("blacklistSearchCount");

		try {
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, text);
			pstmt.setString(2, text);
			pstmt.setString(3, text);
			pstmt.setString(4, text);
			pstmt.setString(5, text);
			pstmt.setString(6, text);
			

			rset = pstmt.executeQuery();

			//이 부분이 맞나 싶다
			if(rset.next()) {
				listCount = rset.getInt(1);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);

		}

		return listCount;
	}


	public int updateBlackList(Connection con, String blackText) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBlackList");
		
		
		return 0;
	}
public ArrayList<DetailMember> selectInvRankList(Connection con, int currentPage, int limit) {
		
		
		ArrayList<DetailMember> rankList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectAllInvRank");
		System.out.println("제발 여기까진 와주라");
		try {
			pstmt= con.prepareStatement(query);

			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;


			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rankList = new ArrayList<DetailMember>();
			rset = pstmt.executeQuery();
			while(rset.next()){
				DetailMember dm = new DetailMember();		
			
				dm.setUserId(rset.getInt("user_id"));
				dm.setUserName(rset.getString("user_name"));
				dm.setInvestorGrade(rset.getString("investor_grade"));
				dm.setF_id(rset.getInt("fid"));
				
				
				rankList.add(dm);
			};

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}
		System.out.println("랭크리스트는~" + rankList);

		return rankList;
	}

	public int getInvRankListCount(Connection con) {

		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("invRankCount");

		try {
			pstmt = con.prepareStatement(query);
			
			
			
			rset =	pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);

		}

		return listCount;
	}

	public int updateRank(Connection con, int userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		query = prop.getProperty("updateRank");

		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "wait");
			pstmt.setInt(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

public int getProjectListCount(Connection con) {
	Statement stmt = null;
	int listCount = 0;
	ResultSet rset = null;

	String query = prop.getProperty("projectAllListCount");

	try {
		stmt = con.createStatement();
		rset = stmt.executeQuery(query);

		if(rset.next()) {
			listCount = rset.getInt(1);

		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally{
		close(stmt);
		close(rset);

	}


	return listCount;
}

//프로젝트 전체 조회
public ArrayList<CheckProject> selectAllProject(Connection con, int currentPage, int limit) {
	ArrayList<CheckProject> list = null;

	PreparedStatement pstmt = null;
	ResultSet rset = null;
	System.out.println("dao까지는 오는거지?");
	String query = prop.getProperty("projectSelectAll");

	try {
		pstmt = con.prepareStatement(query);

		int startRow = (currentPage -1) * limit + 1;
		int endRow = startRow + limit -1;

		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);

		rset = pstmt.executeQuery();

		list = new ArrayList<CheckProject>();

		while(rset.next()){
			CheckProject cp = new CheckProject();
			
			cp.setP_pName(rset.getString("p_Name"));				
			cp.setM_userName(rset.getString("user_Name"));
			cp.setP_closingAmount(rset.getInt("closing_Amount"));
			cp.setTest(rset.getDate("test"));
			cp.setPt_pName(rset.getString("p_Name_1"));
			cp.setP_interestRate(rset.getString("interest_Rate"));
			cp.setResult(rset.getInt("result"));
			cp.setPr_status(rset.getString("status"));
			
			
			list.add(cp);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally{
		close(pstmt);
		close(rset);
	}

	System.out.println("list 출력 : " + list);
	return list;
}

//펀딩 정렬 메소드
public ArrayList<CheckProject> sortProject(Connection con, int currentPage, int limit, String sort) {

	ArrayList<CheckProject> list = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;

	String query1 = prop.getProperty("sortProjectP_name");
	String query2 = prop.getProperty("sortProjectUser_name");
	String query3 = prop.getProperty("sortProjectTest");
	String query4 = prop.getProperty("sortProjectResult");
	

	try {
		if(sort.equals("p_name")){
			pstmt = con.prepareStatement(query1);
		}else if(sort.equals("user_name")){
			pstmt = con.prepareStatement(query2);
		}else if(sort.equals("test")){
			pstmt = con.prepareStatement(query3);
		}else if(sort.equals("result")){
			pstmt = con.prepareStatement(query4);
		}
		
		int startRow = (currentPage -1) * limit + 1;
		int endRow = startRow + limit -1;

		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);

		rset = pstmt.executeQuery();

		list = new ArrayList<CheckProject>();

		while(rset.next()){
			CheckProject cp = new CheckProject();
			
			cp.setP_pName(rset.getString("p_Name"));				
			cp.setM_userName(rset.getString("user_Name"));
			cp.setP_closingAmount(rset.getInt("closing_Amount"));
			cp.setTest(rset.getDate("test"));
			cp.setPt_pName(rset.getString("p_Name_1"));
			cp.setP_interestRate(rset.getString("interest_Rate"));
			cp.setResult(rset.getInt("result"));
			cp.setPr_status(rset.getString("status"));
			
			
			list.add(cp);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
		close(rset);
	}


	System.out.println("Project list 가져왔어요" + list);
	return list;

}

//펀딩 검색 메소드
public ArrayList<CheckProject> searchProject(Connection con, int currentPage, int limit, String text) {
	ArrayList<CheckProject> list = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;

	String query = prop.getProperty("searchProject");
	System.out.println("제발 여기까진 와주라");
	try {
		pstmt= con.prepareStatement(query);

		int startRow = (currentPage -1) * limit + 1;
		int endRow = startRow + limit -1;

		pstmt.setString(1, text);
		pstmt.setString(2, text);
		pstmt.setInt(3, startRow);
		pstmt.setInt(4, endRow);

		list = new ArrayList<CheckProject>();
		rset = pstmt.executeQuery();
		while(rset.next()){
			CheckProject cp = new CheckProject();
			
			cp.setP_pName(rset.getString("p_Name"));				
			cp.setM_userName(rset.getString("user_Name"));
			cp.setP_closingAmount(rset.getInt("closing_Amount"));
			cp.setTest(rset.getDate("test"));
			cp.setPt_pName(rset.getString("p_Name_1"));
			cp.setP_interestRate(rset.getString("interest_Rate"));
			cp.setResult(rset.getInt("result"));
			cp.setPr_status(rset.getString("status"));
			
			
			list.add(cp);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally{
		close(pstmt);
		close(rset);
	}
	System.out.println("리스트는~" + list);

	return list;
}

public int searchProjectListCount(Connection con, String text) {
	PreparedStatement pstmt = null;
	int searchList = 0;
	ResultSet rset = null;

	String query = prop.getProperty("searchProjectCount");

	try {
		pstmt = con.prepareStatement(query);

		pstmt.setString(1, text);
		pstmt.setString(2, text);
		
		

		rset = pstmt.executeQuery();

		if(rset.next()) {
			searchList = rset.getInt(1);

		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally{
		close(pstmt);
		close(rset);

	}


	return searchList;
}
//투자자 일 때 상세 정보 메소드
public DetailMember selectOneInv(Connection con, int user_id) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;

	DetailMember dm = null;

	String query = prop.getProperty("selectOneInv1");

	try {
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, user_id);
		pstmt.setInt(2, user_id);
	
		
		rset = pstmt.executeQuery();

		if(rset.next()) {
			dm = new DetailMember();

			dm.setUserId(rset.getInt("user_id"));
			dm.setInvestorGrade(rset.getString("investor_grade"));
			dm.setUserName(rset.getString("user_name"));
			dm.setNickName(rset.getString("nick_name"));
			dm.setEmail(rset.getString("email"));
			dm.setPhone(rset.getString("phone"));
			dm.setEnrollDate(rset.getDate("enroll_date"));
			dm.setCount(rset.getInt("count"));

		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
		close(rset);
	}
	System.out.println("객체 dm1 은 ? = " + dm);
	return dm;
}

public DetailMember selectOneInv2(Connection con, int user_id) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;

	DetailMember dm2 = null;

	String query = prop.getProperty("selectOneInv2");

	try {
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, user_id);
		
		
		rset = pstmt.executeQuery();

		if(rset.next()) {
			dm2 = new DetailMember();

			dm2.setTotalInvest(rset.getInt("total_invest"));
		

		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
		close(rset);
	}
	System.out.println("객체 dm2 은 ? = " + dm2);
	return dm2;
}

//사업자 일 때 상세 정보 메소드
public DetailMember selectOneEnp(Connection con, int user_id) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;

	DetailMember dm = null;

	String query = prop.getProperty("selectOneEnp");

	try {
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, user_id);
		pstmt.setInt(2, user_id);
		pstmt.setInt(3, user_id);
		
		rset = pstmt.executeQuery();

		if(rset.next()) {
			dm = new DetailMember();

			dm.setUserId(rset.getInt("user_id"));
			dm.setUserClass(rset.getString("user_class"));
			dm.setUserName(rset.getString("user_name"));
			dm.setCorporateName(rset.getString("corporate_name"));
			dm.setNickName(rset.getString("nick_name"));
			dm.setEmail(rset.getString("email"));
			dm.setPhone(rset.getString("phone"));
			dm.setEnrollDate(rset.getDate("enroll_date"));
			dm.setProductCount(rset.getInt("product_count"));
			dm.setFinalResult(rset.getInt("finalresult"));

		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
		close(rset);
	}
	System.out.println("객체 dm 은 ? = " + dm);
	return dm;
}

	
	
	
	
	////////////////////////////////여기서부터 별림이꺼/////////////////////////////////////
	
	//매출 통계
	
	//전체월별통계
	public ArrayList<SalesStatistics> selectAllSalesMonthList(Connection con, int currentPage, int limit) {
		ArrayList<SalesStatistics> list = new ArrayList<SalesStatistics>();
		SalesStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		String query = prop.getProperty("selectAllSalesMonth");
			
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
	    	pstmt.setInt(2, endRow);
	            
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new SalesStatistics();
				
				result.setTerm(rset.getString("term"));
				result.setPaymentCount(rset.getInt("payment_count"));
				result.setPaymentPrice(rset.getString("payment_price"));
				result.setRefundCount(rset.getInt("refund_count"));
				result.setPaymentPercentage(rset.getInt("payment_percentage"));
				result.setPaymentCompletePrice(rset.getString("payment_complete_price"));
				result.setNetSales(rset.getString("net_sales"));
				
				
				list.add(result);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

	//타입별 월별 통계
	public ArrayList<SalesStatistics> selectSalesTypeMonthList(Connection con, String type, int currentPage, int limit) {
		ArrayList<SalesStatistics> list = new ArrayList<SalesStatistics>();
		SalesStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectSalesTypeMonth");
		
		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		
		try {
			pstmt = con.prepareStatement(query);
			
			if(type.equals("t1")){
	              pstmt.setString(1, "1");
	           }else if(type.equals("t2")){
	              pstmt.setString(1, "2");
	           }else{
	              pstmt.setString(1, "3");
	           }
	       pstmt.setInt(2, startRow);
	       pstmt.setInt(3, endRow);
	         
		   rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new SalesStatistics();
				
				result.setTerm(rset.getString("term"));
				result.setPaymentCount(rset.getInt("payment_count"));
				result.setPaymentPrice(rset.getString("payment_price"));
				result.setRefundCount(rset.getInt("refund_count"));
				result.setPaymentPercentage(rset.getInt("payment_percentage"));
				result.setPaymentCompletePrice(rset.getString("payment_complete_price"));
				result.setNetSales(rset.getString("net_sales"));
				
				
				list.add(result);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

    //전체 년도별 매출 통계
	public ArrayList<SalesStatistics> selectAllSalesYearList(Connection con, int currentPage, int limit) {
		ArrayList<SalesStatistics> list = new ArrayList<SalesStatistics>();
		SalesStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllSalesYear");
		
		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
	    	pstmt.setInt(2, endRow) ;
	         
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new SalesStatistics();
				
				result.setTerm(rset.getString("term"));
				result.setPaymentCount(rset.getInt("payment_count"));
				result.setPaymentPrice(rset.getString("payment_price"));
				result.setRefundCount(rset.getInt("refund_count"));
				result.setPaymentPercentage(rset.getInt("payment_percentage"));
				result.setPaymentCompletePrice(rset.getString("payment_complete_price"));
				result.setNetSales(rset.getString("net_sales"));
				
				
				list.add(result);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	

	public ArrayList<SalesStatistics> selectSalesTypeYearList(Connection con, String type, int currentPage, int limit) {
		ArrayList<SalesStatistics> list = new ArrayList<SalesStatistics>();
		SalesStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectSalesTypeYear");
		
		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);

			if(type.equals("t1")){
				pstmt.setString(1, "1");
			}else if(type.equals("t2")){
				pstmt.setString(1, "2");
			}else{
				pstmt.setString(1, "3");
			}
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new SalesStatistics();
				
				result.setTerm(rset.getString("term"));
				result.setPaymentCount(rset.getInt("payment_count"));
				result.setPaymentPrice(rset.getString("payment_price"));
				result.setRefundCount(rset.getInt("refund_count"));
				result.setPaymentPercentage(rset.getInt("payment_percentage"));
				result.setPaymentCompletePrice(rset.getString("payment_complete_price"));
				result.setNetSales(rset.getString("net_sales"));
				
				
				list.add(result);
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	
    //개설 펀딩 통계
	
	//월별 개설 펀딩 통계
	public ArrayList<OpenFundingStatistics> selectOpenFundingMonthList(Connection con, int currentPage, int limit) {
		ArrayList<OpenFundingStatistics> list = new ArrayList<OpenFundingStatistics>();
		OpenFundingStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOpenFundingMonth");

		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
            rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new OpenFundingStatistics();
				
				result.setTerm(rset.getString("term"));
				result.setEnrollCount(rset.getInt("enroll_count"));
				result.setOpenCount(rset.getInt("open_count"));
				result.setApprovalRate(rset.getInt("approval_rate"));
				result.setType1OpenCount(rset.getInt("type1_open_count"));
				result.setType2OpenCount(rset.getInt("type2_open_count"));
				result.setType3OpenCount(rset.getInt("type3_open_count"));
				
				list.add(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}
    //년도별 개설 펀딩 통계
	public ArrayList<OpenFundingStatistics> selectOpenFundingYearList(Connection con, int currentPage, int limit) {
		ArrayList<OpenFundingStatistics> list = new ArrayList<OpenFundingStatistics>();
		OpenFundingStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOpenFundingYear");

		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
            rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new OpenFundingStatistics();
				
				result.setTerm(rset.getString("term"));
				result.setEnrollCount(rset.getInt("enroll_count"));
				result.setOpenCount(rset.getInt("open_count"));
				result.setApprovalRate(rset.getInt("approval_rate"));
				result.setType1OpenCount(rset.getInt("type1_open_count"));
				result.setType2OpenCount(rset.getInt("type2_open_count"));
				result.setType3OpenCount(rset.getInt("type3_open_count"));
				
				list.add(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}
	
	//성공 펀딩 통계
	
    //월별 성공 펀딩 통계
	public ArrayList<SuccessFundingStatistics> selectSuccessFundingMonthList(Connection con, int currentPage,
			int limit) {
		ArrayList<SuccessFundingStatistics> list = new ArrayList<SuccessFundingStatistics>();
		SuccessFundingStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectSuccessFundingMonth");
		
		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
            rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new SuccessFundingStatistics();
				result.setTerm(rset.getString("term"));
				result.setEndCount(rset.getInt("end_count"));
				result.setSuccessCount(rset.getInt("success_count"));
				result.setSuccessRate(rset.getInt("success_rate"));
				result.setType1SuccessRate(rset.getInt("type1_success_rate"));
				result.setType2SuccessRate(rset.getInt("type2_success_rate"));
				result.setType3SuccessRate(rset.getInt("type3_success_rate"));
				
				list.add(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
    //년도별 성공 펀딩 통계
	public ArrayList<SuccessFundingStatistics> selectSuccessFundingYearList(Connection con, int currentPage,
			int limit) {
		ArrayList<SuccessFundingStatistics> list = new ArrayList<SuccessFundingStatistics>();
		SuccessFundingStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectSuccessFundingYear");
		
		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
            rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new SuccessFundingStatistics();
				result.setTerm(rset.getString("term"));
				result.setEndCount(rset.getInt("end_count"));
				result.setSuccessCount(rset.getInt("success_count"));
				result.setSuccessRate(rset.getInt("success_rate"));
				result.setType1SuccessRate(rset.getInt("type1_success_rate"));
				result.setType2SuccessRate(rset.getInt("type2_success_rate"));
				result.setType3SuccessRate(rset.getInt("type3_success_rate"));
				
				list.add(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	
	//가입자 통계
	
	//일별 가입자 통계
	public ArrayList<MemberStatistics> selectMemberDateList(Connection con, int currentPage, int limit) {
		ArrayList<MemberStatistics> list = new ArrayList<MemberStatistics>();
		MemberStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberDate");
		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
            rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new MemberStatistics();
				
				result.setTerm(rset.getString("term"));
				result.setMemberCount(rset.getInt("member_count"));
				result.setEmailMemberCount(rset.getInt("email_member_count"));
				result.setKakaoMemberCount(rset.getInt("kakao_member_count"));
				result.setNaverMemberCount(rset.getInt("naver_member_count"));

				
				list.add(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	
    //월별 가입자 통계
	public ArrayList<MemberStatistics> selectMemberMonthList(Connection con, int currentPage, int limit) {
		ArrayList<MemberStatistics> list = new ArrayList<MemberStatistics>();
		MemberStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberMonth");
		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
            rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new MemberStatistics();
				
				result.setTerm(rset.getString("term"));
				result.setMemberCount(rset.getInt("member_count"));
				result.setEmailMemberCount(rset.getInt("email_member_count"));
				result.setKakaoMemberCount(rset.getInt("kakao_member_count"));
				result.setNaverMemberCount(rset.getInt("naver_member_count"));

				
				list.add(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	
    //년도별 가입자 통계
	public ArrayList<MemberStatistics> selectMemberYearList(Connection con, int currentPage, int limit) {
		ArrayList<MemberStatistics> list = new ArrayList<MemberStatistics>();
		MemberStatistics result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberYear");
		int startRow = (currentPage -1 ) * limit + 1;
		int endRow = startRow + limit - 1;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
            rset = pstmt.executeQuery();
			
			while(rset.next()){
				result = new MemberStatistics();
				
				result.setTerm(rset.getString("term"));
				result.setMemberCount(rset.getInt("member_count"));
				result.setEmailMemberCount(rset.getInt("email_member_count"));
				result.setKakaoMemberCount(rset.getInt("kakao_member_count"));
				result.setNaverMemberCount(rset.getInt("naver_member_count"));

				
				list.add(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	

	//매출 리스트 카운트(페이징)
	
    //전체 월별 매출 리스트 카운트
	public int getSalesMonthListCount(Connection con) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
		String query = prop.getProperty("listSalesMonthCount");

		try {
			pstmt = con.prepareStatement(query);
           
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);

		}

		return listCount;
	}
	
    //타입별 월별 매출 리스트 카운트
	public int getSalesTypeMonthListCount(Connection con, String type) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
		String query = prop.getProperty("listSalesTypeMonthCount");

		try {
			pstmt = con.prepareStatement(query);
            if(type != null){
            	if(type.equals("t1")){
            		pstmt.setString(1, "1");
            	}else if(type.equals("t2")){
            		pstmt.setString(1, "2");
            	}else{
            		pstmt.setString(1, "3");
            	}
            }
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);

		}

		return listCount;
	}
	
    //전체 년도별 매출 리스트 카운트
	public int getSalesYearListCount(Connection con) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
		String query = prop.getProperty("listSalesYearCount");

		try {
			pstmt = con.prepareStatement(query);
            
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);

		}

		return listCount;
	}
	
    //타입별 년도별 매출 리스트 카운트
	public int getSalesTypeYearListCount(Connection con, String type) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
		String query = prop.getProperty("listSalesTypeYearCount");

		try {
			pstmt = con.prepareStatement(query);
            if(type != null){
            	if(type.equals("t1")){
            		pstmt.setString(1, "1");
            	}else if(type.equals("t2")){
            		pstmt.setString(1, "2");
            	}else{
            		pstmt.setString(1, "3");
            	}
            }
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);

		}

		return listCount;
	}
	
	//개설 펀딩 리스트 카운트(페이징)
	
    //월별 개설 펀딩 리스트 카운트
	public int getOpenFundingMonthListCount(Connection con) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
		String query = prop.getProperty("listOpenFundingMonthCount");

		try {
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);

		}

		return listCount;
	}
	
    //년도별 개설 펀딩 리스트 카운트
	public int getOpenFundingYearListCount(Connection con) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
		String query = prop.getProperty("listOpenFundingYearCount");

		try {
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);

		}

		return listCount;
	}
	
    
	//성공 펀딩 리스트 카운트(페이징)
	//월별 성공 펀딩 리스트 카운트
	public int getSuccessFundingMonthListCount(Connection con) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
        String query = prop.getProperty("listSuccessFundingMonthCount");
        
		try {
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}

		return listCount;
	}
    //년도별 성공 펀딩 리스트 카운트
	public int getSuccessFundingYearListCount(Connection con) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
        String query = prop.getProperty("listSuccessFundingYearCount");
        
		try {
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}

		return listCount;
	}
	
	//가입자 리스트 카운트(페이징)
	
    //일별 가입자 리스트 
	public int getMemberDateListCount(Connection con, String term) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
        String query = prop.getProperty("listMemberDateCount");
        
		try {
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);

		}

		return listCount;
	}
	
    //월별 가입자 리스트
	public int getMemberMonthListCount(Connection con, String term) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
        String query = prop.getProperty("listMemberMonthCount");
        
		try {
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}

		return listCount;
	}
    //년도별 가입자 리스트
	public int getMemberYearListCount(Connection con, String term) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
        String query = prop.getProperty("listMemberYearCount");
        
		try {
			pstmt = con.prepareStatement(query);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}

		return listCount;
	}

	public Attachment downloadFileAttachment(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Attachment file = null;
		
		String query = prop.getProperty("downloadAttachment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				file = new Attachment();
/*				(FID, ORIGIN_NAME, CHANGE_NAME, FILE_PATH, 
				* UPLOAD_DATE, P_TYPE, STATUS, USER_ID) 
*/
				file.setFid(rset.getInt("fid"));
				file.setOriginName(rset.getString("origin_name"));
				file.setChangeName(rset.getString("change_name"));
				file.setFilePath(rset.getString("file_path"));
				file.setUploadDate(rset.getDate("upload_date"));
				file.setpType(rset.getInt("p_type"));
				file.setStatus(rset.getString("status"));
				file.setUserId(rset.getString("user_id"));

				
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
			
		}
		
		
		
		
		return file;
	}

	public int updateRank2(Connection con, int userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		query = prop.getProperty("updateRank2");

		try {
			
			pstmt = con.prepareStatement(query);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int getDlineListCount(Connection con) {
		Statement stmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("dlineCount");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				listCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(stmt);
			close(rset);

		}


		return listCount;
	}
	

	public ArrayList<DetailMember> selectDlineList(Connection con, int currentPage, int limit) {
		ArrayList<DetailMember> dlineList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectAllDline");
		System.out.println("selectAllDline Dao입니다");
		try {
			pstmt= con.prepareStatement(query);

			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			dlineList = new ArrayList<DetailMember>();
			rset = pstmt.executeQuery();
			while(rset.next()){
				DetailMember dm = new DetailMember();		
				//P_ID, P_NAME, test,  CORPORATE_NAME, CLOSING_AMOUNT, RESULT , FEE
				
				dm.setP_pId(rset.getInt("p_id"));
				dm.setP_pName(rset.getString("p_name"));
				dm.setTest(rset.getDate("test"));
				dm.setCorporateName(rset.getString("corporate_name"));
				dm.setP_closingAmount(rset.getInt("closing_amount"));
				dm.setResult(rset.getInt("result"));
				dm.setFee(rset.getString("fee"));
				
				dlineList.add(dm);
			};

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}
		System.out.println("마감리스트는~" + dlineList);

		return dlineList;
	}

	public int updateDline(Connection con, int p_Id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		query = prop.getProperty("updateDline");

		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, p_Id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updateDline2(Connection con, int p_Id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		query = prop.getProperty("updateDline2");

		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, p_Id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public ArrayList<DetailMember> selectConfirmList(Connection con, int currentPage, int limit) {
		ArrayList<DetailMember> confirmList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectAllConfirm");
		System.out.println("selectAllDline Dao입니다");
		try {
			pstmt= con.prepareStatement(query);

			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			confirmList = new ArrayList<DetailMember>();
			rset = pstmt.executeQuery();
			while(rset.next()){
				DetailMember dm = new DetailMember();		
				
				dm.setUserId(rset.getInt("user_id"));
				dm.setUserName(rset.getString("user_name"));
				dm.setP_pId(rset.getInt("p_id"));
				dm.setP_pName(rset.getString("p_name"));
			
				confirmList.add(dm);
			};

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}
		System.out.println("심사리스트는~" + confirmList);

		return confirmList;
	}

	public int getConfirmListCount(Connection con) {
		Statement stmt = null;
		int listCount = 0;
		ResultSet rset = null;

		String query = prop.getProperty("confirmCount");

		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if(rset.next()) {
				listCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(stmt);
			close(rset);

		}


		return listCount;
	}

	public int updateConfirm(Connection con, int p_Id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		query = prop.getProperty("updateConfirm");

		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, p_Id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updateConfirm2(Connection con, int p_Id) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		query = prop.getProperty("updateConfirm2");

		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, p_Id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Attachment userDownloadFileAttachment(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Attachment file = null;
		
		String query = prop.getProperty("downloadAttachment");
		
		try { 
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				file = new Attachment();
				
				file.setFid(rset.getInt("fid"));
				file.setBid(rset.getInt("bid"));
				file.setOriginName(rset.getString("origin_name"));
				file.setChangeName(rset.getString("change_name"));
				file.setFilePath(rset.getString("file_path"));
				file.setUploadDate(rset.getDate("upload_date"));
				file.setFileLevel(rset.getInt("file_level"));
				file.setDownloadCount(rset.getInt("download_count"));
				file.setStatus(rset.getString("status"));
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
			close(rset);
		}
		
		
		
		return file;
		}
	}