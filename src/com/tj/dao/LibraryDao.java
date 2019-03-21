package com.tj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.dto.LibraryDto;

public class LibraryDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	public LibraryDao() {
		try {
			Context ctx = new InitialContext();
			ds = 
			(DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 책목록
	public ArrayList<LibraryDto> bookList(int startRow, int endRow){
		ArrayList<LibraryDto> dtos = new ArrayList<LibraryDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM  " + 
				"        (SELECT ROWNUM RN, A.* FROM  " + 
				"            (SELECT * FROM LIBRARY WHERE LDEL='N' ORDER BY LMANY, LTITLE DESC) A)  " + 
				"            WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String lCallnum		= rs.getString("lCallnum");
				String lTitle		= rs.getString("lTitle");
				String lWriter		= rs.getString("lWriter");
				int    lYear		= rs.getInt("lYear");
				String lPublicsher	= rs.getString("lPublicsher");
				String lLocation	= rs.getString("lLocation");
				String lImage		= rs.getString("lImage");
				String lKeyword		= rs.getString("lKeyword");
				String lBorrow		= rs.getString("lBorrow");
				int    lMany		= rs.getInt("lMany");
				int    bNumber		= rs.getInt("bNumber");
				dtos.add(new LibraryDto(lCallnum, lTitle, lWriter, lYear, lPublicsher, 
						lLocation, lImage, lKeyword, lBorrow, lMany, bNumber));
			}
		}catch (SQLException e){
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
	//신작4권 가져오기
	public ArrayList<LibraryDto> newBookList(){
		ArrayList<LibraryDto> dtos = new ArrayList<LibraryDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM  " + 
				"        (SELECT ROWNUM RN, A.* FROM  " + 
				"            (SELECT * FROM LIBRARY WHERE LDEL='N' ORDER BY LCALLNUM+0 DESC) A)  " + 
				"            WHERE RN BETWEEN 1 AND 4";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String lCallnum		= rs.getString("lCallnum");
				String lTitle		= rs.getString("lTitle");
				String lWriter		= rs.getString("lWriter");
				int    lYear		= rs.getInt("lYear");
				String lPublicsher	= rs.getString("lPublicsher");
				String lLocation	= rs.getString("lLocation");
				String lImage		= rs.getString("lImage");
				String lKeyword		= rs.getString("lKeyword");
				String lBorrow		= rs.getString("lBorrow");
				int    lMany		= rs.getInt("lMany");
				int    bNumber		= rs.getInt("bNumber");
				dtos.add(new LibraryDto(lCallnum, lTitle, lWriter, lYear, lPublicsher, 
						lLocation, lImage, lKeyword, lBorrow, lMany, bNumber));
			}
		}catch (SQLException e){
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
	//책검색하기
	public ArrayList<LibraryDto> bookList(int startRow, int endRow, String slTitle){
		ArrayList<LibraryDto> dtos = new ArrayList<LibraryDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM  " + 
				"        (SELECT ROWNUM RN, A.* FROM  " + 
				"            (SELECT * FROM LIBRARY WHERE LTITLE LIKE '%'||?||'%' " +
				"		OR lKeyword LIKE '%'||?||'%' AND LDEL='N' ORDER BY LMANY, LTITLE DESC) A)  " + 
				"            WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, slTitle);
			pstmt.setString(2, slTitle);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String lCallnum		= rs.getString("lCallnum");
				String lTitle		= rs.getString("lTitle");
				String lWriter		= rs.getString("lWriter");
				int    lYear		= rs.getInt("lYear");
				String lPublicsher	= rs.getString("lPublicsher");
				String lLocation	= rs.getString("lLocation");
				String lImage		= rs.getString("lImage");
				String lKeyword		= rs.getString("lKeyword");
				String lBorrow		= rs.getString("lBorrow");
				int    lMany		= rs.getInt("lMany");
				int    bNumber		= rs.getInt("bNumber");
				dtos.add(new LibraryDto(lCallnum, lTitle, lWriter, lYear, lPublicsher, 
						lLocation, lImage, lKeyword, lBorrow, lMany, bNumber));
			}
		}catch (SQLException e){
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
	// 도서등록
	public int insertBook(String lTitle, String lWriter, int lYear,
			String lPublicsher, String lLocation, String lImage, String lKeyword, int bNumber) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO LIBRARY (lCallnum, lTitle, lWriter, lYear, lPublicsher, "
				+ "			lLocation, lImage, lKeyword, bNumber) " + 
				"    VALUES (LIBRARY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lTitle);
			pstmt.setString(2, lWriter);
			pstmt.setInt(3, lYear);
			pstmt.setString(4, lPublicsher);
			pstmt.setString(5, lLocation);
			pstmt.setString(6, lImage);
			pstmt.setString(7, lKeyword);
			pstmt.setInt(8, bNumber);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "책입력성공":"책입력실패");
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
	// 도서 상세 보기
	public LibraryDto bookView(String lCallnum) {
		LibraryDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM LIBRARY WHERE lCallnum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCallnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String lTitle	   = rs.getString("lTitle");
				String lWriter	   = rs.getString("lWriter");
				int    lYear	   = rs.getInt("lYear");
				String lPublicsher = rs.getString("lPublicsher");
				String lLocation   = rs.getString("lLocation");
				String lImage	   = rs.getString("lImage");
				String lKeyword	   = rs.getString("lKeyword");
				String lBorrow	   = rs.getString("lBorrow");
				int    lMany	   = rs.getInt("lMany");
				int    bNumber	   = rs.getInt("bNumber");
				dto = new LibraryDto(lCallnum, lTitle, lWriter, lYear, lPublicsher,
						lLocation, lImage, lKeyword, lBorrow, lMany, bNumber);
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
	public int modifyBook(String lCallnum, String lTitle, String lWriter, int lYear, 
			String lPublicsher,	String lLocation, String lImage, String lKeyword, int bNumber) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LIBRARY SET LTITLE = ?," + 
				"                        LWRITER = ?," + 
				"                        LYEAR = ?," + 
				"                        LPUBLICSHER = ?," + 
				"                        LLOCATION = ?," + 
				"                        LIMAGE = ?," + 
				"                        LKEYWORD = ?," + 
				"                        BNUMBER = ?" + 
				"                WHERE lCallnum = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lTitle);
			pstmt.setString(2, lWriter);
			pstmt.setInt(3, lYear);
			pstmt.setString(4, lPublicsher);
			pstmt.setString(5, lLocation);
			pstmt.setString(6, lImage);
			pstmt.setString(7, lKeyword);
			pstmt.setInt(8, bNumber);
			pstmt.setString(9, lCallnum);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "책수정성공":"책수정실패");
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
	public int deleteBook(String lCallnum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LIBRARY SET lDel = 'Y' WHERE lCallnum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCallnum);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "책삭제성공":"책삭제실패");
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
	//대출횟수 1회 증가하고 빌린상태로 만들기
	public int upReadCount(String lCallnum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LIBRARY SET LMANY = LMANY+1, LBORROW = 'N' WHERE lCallnum=?";
		try {
			conn = ds.getConnection();pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCallnum);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "read1up성공":"read1up실패");
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
	
	// 도서 갯수
	public int getBookCnt() {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet	rs = null;
		String sql = "SELECT COUNT(*) FROM LIBRARY WHERE LDEL='N'";
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 검색한 도서 갯수
		public int getBookCnt(String slTitle) {
			int result = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet	rs = null;
			String sql = "SELECT COUNT(*) FROM LIBRARY WHERE LTITLE LIKE '%'||?||'%' OR lKeyword LIKE '%'||?||'%'";
			try {
				conn = ds.getConnection();
				pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, slTitle);
				pstmt.setString(2, slTitle);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return result;
		}
		//내가 빌린책
		public ArrayList<LibraryDto> myBookList(String mId) {
			ArrayList<LibraryDto> dto = new ArrayList<LibraryDto>();
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM LIBRARY L, LIBRARYBORROW B WHERE L.LCALLNUM = B.LCALLNUM "
					+ "AND B.mId=? AND LBREALRE IS NULL";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String lCallnum    = rs.getString("lCallnum");
					String lTitle	   = rs.getString("lTitle");
					String lWriter	   = rs.getString("lWriter");
					int    lYear	   = rs.getInt("lYear");
					String lImage	   = rs.getString("lImage");
					String lPublicsher = rs.getString("lPublicsher");
					String lLocation   = rs.getString("lLocation");
					String lKeyword	   = rs.getString("lKeyword");
					String lBorrow	   = rs.getString("lBorrow");
					int    bNumber	   = rs.getInt("bNumber");
					dto.add(new LibraryDto(lCallnum, lTitle, lWriter, lYear, lPublicsher, 
							lLocation, lImage, lKeyword, lBorrow, 0, bNumber));
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
		// 내가 빌린 책갯수
		public int getMyBookCnt(String mId) {
			int result = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet	rs = null;
			String sql = "SELECT COUNT(*) FROM LIBRARY L, LIBRARYBORROW B WHERE L.LCALLNUM = B.LCALLNUM  " + 
					"AND B.mId=? AND LBREALRE IS NULL";
			try {
				conn = ds.getConnection();
				pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return result;
		}
}
