package com.tj.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.dto.LibraryBorrowDto;

public class LibraryBorrowDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	public LibraryBorrowDao() {
		try {
			Context ctx = new InitialContext();
			ds = 
			(DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	//대출처리
	public int lbBorrowBook(String lCallnum, String mId) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO LIBRARYBORROW (LBNUM, lCallnum, MID) " + 
				"    VALUES (LIBRARYBORROW_SEQ.NEXTVAL, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCallnum);
			pstmt.setString(2, mId);
			result = pstmt.executeUpdate();
			if(result==SUCCESS) {
				borrowBook(lCallnum);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return result;
	}
	//빌린상태로 만들기
	public void borrowBook(String lCallnum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LIBRARY SET LBORROW = 'N' WHERE lCallnum=?";
		try {
			conn = ds.getConnection();pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCallnum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
	}
	//반납처리
	public int lbReturnBook(String lCallnum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet		  rs	= null;
		String sql = "SELECT * FROM LIBRARYBORROW WHERE lCallnum=? AND LBREALRE IS NULL";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCallnum);
			rs = pstmt.executeQuery();
			rs.next();
			int lbNum = rs.getInt("lbNum");
			rs.next();
			returnBook(lCallnum);
			returnDate(lbNum);
			result=SUCCESS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	// 빌리지 않은 상태로 만들기
		public void returnBook(String lCallnum) {
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE LIBRARY SET LBORROW = 'Y' WHERE lCallnum=?";
			try {
				conn = ds.getConnection();pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, lCallnum);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {System.out.println(e.getMessage());}
			}
		}
	//반납일 생성하기
		public void returnDate(int lbNum) {
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE LIBRARYBORROW SET lbREALre = SYSDATE WHERE lbNum=?";
			try {
				conn = ds.getConnection();pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, lbNum);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {System.out.println(e.getMessage());}
			}
		}
	
	//내가 빌린책
	public ArrayList<LibraryBorrowDto> borrowList(String mId){
		ArrayList<LibraryBorrowDto> dtos = new ArrayList<LibraryBorrowDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT L.lCallnum, LTITLE, LWRITER, LIMAGE, lbDate, lbREdate FROM LIBRARY L, "
				+ 		"LIBRARYBORROW B WHERE L.lCallnum = B.lCallnum AND B.mId=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String lCallnum	= rs.getString("lCallnum");
				String lTitle	= rs.getString("lTitle");
				String lWriter	= rs.getString("lWriter");
				String lImage	= rs.getString("lImage");
				Date   lbDate	= rs.getDate("lbDate");
				Date   lbREdate	= rs.getDate("lbREdate");
				dtos.add(new LibraryBorrowDto(0, lCallnum, mId, lbDate, lbREdate, null, lTitle, lWriter, lImage));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return dtos;
	}
	//대출되어있는책 보기 ADMIN
	public ArrayList<LibraryBorrowDto> noReturnBorrowList(int startRow, int endRow){
		ArrayList<LibraryBorrowDto> dtos = new ArrayList<LibraryBorrowDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM " + 
				"        (SELECT ROWNUM RN, A.* FROM " + 
				"            (SELECT B.*, LTITLE FROM LIBRARYBORROW B, LIBRARY L "
				+ "				WHERE B.lCallnum=L.lCallnum AND LBREALRE IS NULL ORDER BY lbNum DESC) A) " + 
				"            WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int lbNum		= rs.getInt("lbNum");
				String lTitle	= rs.getString("lTitle");
				String lCallnum	= rs.getString("lCallnum");
				String mId		= rs.getString("mId");
				Date   lbDate	= rs.getDate("lbDate");
				Date   lbREdate	= rs.getDate("lbREdate");
				dtos.add(new LibraryBorrowDto(lbNum, lCallnum, mId, lbDate, lbREdate, null, lTitle, null, null));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return dtos;
	}
	//현황보기
		public ArrayList<LibraryBorrowDto> returnBorrowList(int startRow, int endRow){
			ArrayList<LibraryBorrowDto> dtos = new ArrayList<LibraryBorrowDto>();
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM " + 
					"        (SELECT ROWNUM RN, A.* FROM " + 
					"            (SELECT B.*, LTITLE FROM LIBRARYBORROW B, LIBRARY L "
					+ "				WHERE B.lCallnum=L.lCallnum ORDER BY lbNum DESC) A) " + 
					"            WHERE RN BETWEEN ? AND ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int lbNum		= rs.getInt("lbNum");
					String lTitle	= rs.getString("lTitle");
					String lCallnum	= rs.getString("lCallnum");
					String mId		= rs.getString("mId");
					Date   lbDate	= rs.getDate("lbDate");
					Date   lbREdate	= rs.getDate("lbREdate");
					Date   lbREALre	= rs.getDate("lbREALre");
					dtos.add(new LibraryBorrowDto(lbNum, lCallnum, mId, lbDate, lbREdate, lbREALre, lTitle, null, null));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs   !=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {System.out.println(e.getMessage());}
			}
			return dtos;
		}
		//빌려져있는책 불러오기
		public LibraryBorrowDto getBookNoReturn(String lCallnum){
			LibraryBorrowDto dto = null;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM LIBRARYBORROW WHERE lCallnum=? AND LBREALRE IS NULL";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, lCallnum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int lbNum		= rs.getInt("lbNum");
					String mId		= rs.getString("mId");
					Date   lbDate	= rs.getDate("lbDate");
					Date   lbREdate	= rs.getDate("lbREdate");
					dto = new LibraryBorrowDto(lbNum, lCallnum, mId, lbDate, lbREdate, null);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs   !=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {System.out.println(e.getMessage());}
			}
			return dto;
		}
		//리스트갯수
		public int getListCnt(){
			int cnt = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT COUNT(*) FROM LIBRARYBORROW";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				cnt = rs.getInt(1);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs   !=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {System.out.println(e.getMessage());}
			}
			return cnt;
		}
}
