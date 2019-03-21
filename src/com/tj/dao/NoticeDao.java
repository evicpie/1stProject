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

import com.tj.dto.BoardDto;
import com.tj.dto.NoticeDto;


public class NoticeDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	public NoticeDao() {
		try {
			Context ctx = new InitialContext();
			ds = 
			(DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 글목록
		public ArrayList<NoticeDto> list(int startRow, int endRow){
			ArrayList<NoticeDto> dtos = new ArrayList<NoticeDto>();
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM  " + 
					"        (SELECT ROWNUM RN, A.* FROM  " + 
					"            (SELECT * FROM NOTICE " + 
					"                ORDER BY NNUM DESC) A) WHERE RN BETWEEN ? AND ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int 	nNum	= rs.getInt("nNum");
					String 	nTitle	= rs.getString("nTitle");
					String 	nContent= rs.getString("nContent");
					Date 	nDate	= rs.getDate("nDate");
					int 	nHIT	= rs.getInt("nHIT");
					String 	aId		= rs.getString("aId");
					dtos.add(new NoticeDto(nNum, nTitle, nContent, nDate, nHIT, aId));
				}
			}catch (SQLException e) {
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
		// 글 5개만 가져오기
				public ArrayList<NoticeDto> list5(){
					ArrayList<NoticeDto> dtos = new ArrayList<NoticeDto>();
					Connection        conn  = null;
					PreparedStatement pstmt = null;
					ResultSet         rs    = null;
					String sql = "SELECT * FROM  " + 
							"        (SELECT ROWNUM RN, A.* FROM  " + 
							"            (SELECT * FROM NOTICE " + 
							"                ORDER BY NNUM DESC) A) WHERE RN BETWEEN 1 AND 5";
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							int 	nNum	= rs.getInt("nNum");
							String 	nTitle	= rs.getString("nTitle");
							String 	nContent= rs.getString("nContent");
							Date 	nDate	= rs.getDate("nDate");
							int 	nHIT	= rs.getInt("nHIT");
							String 	aId		= rs.getString("aId");
							dtos.add(new NoticeDto(nNum, nTitle, nContent, nDate, nHIT, aId));
						}
					}catch (SQLException e) {
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
		// 글갯수
		public int getNoticeTotCnt() {
			int cnt = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT COUNT(*) FROM NOTICE";
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
		// 글쓰기
		public int write(String aId, String nTitle, String nContent) {
			int result = FAIL;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO NOTICE (nNum, nTitle, nContent, nDate, nHit, aId) " + 
					"    VALUES (NOTICE_SEQ.NEXTVAL, ?, ?, SYSDATE, 0, ?)";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, nTitle);
				pstmt.setString(2, nContent);
				pstmt.setString(3, aId);
				result = pstmt.executeUpdate();
				System.out.println(result==SUCCESS? "원글쓰기성공":"원글쓰기실패");
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
		// 조회수올리기
		private void hitUp(int nNum) {
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE NOTICE SET nHIT = nHIT +1 WHERE nNum=?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nNum);
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
		// 글 상세보기(조회수 up + nNum로 dto리턴)
		public NoticeDto contentView(int nNum) {
			hitUp(nNum);
			NoticeDto dto = null;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM NOTICE WHERE NNUM=?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nNum);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String nTitle = rs.getString("nTitle");
					String nContent = rs.getString("nContent");
					Date nDate = rs.getDate("nDate");
					int nHIT = rs.getInt("nHIT");
					String aId = rs.getString("aId");
					dto = new NoticeDto(nNum, nTitle, nContent, nDate, nHIT, aId);
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
		// 수정 view (nNum로 dto리턴)
		public NoticeDto modifyView(int nNum) {
			NoticeDto dto = null;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM NOTICE WHERE NNUM=?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nNum);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String nTitle = rs.getString("nTitle");
					String nContent = rs.getString("nContent");
					Date nDate = rs.getDate("nDate");
					int nHIT = rs.getInt("nHIT");
					String aId = rs.getString("aId");
					dto = new NoticeDto(nNum, nTitle, nContent, nDate, nHIT, aId);
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
		// 글 수정하기
		public int modify(int nNum, String nTitle, String nContent) {
			int result = FAIL;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE NOTICE SET NTITLE = ?, " + 
					"                           NCONTENT = ?, " + 
					"                           NDATE = SYSDATE " + 
					"                           WHERE nNUM = ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, nTitle);
				pstmt.setString(2, nContent);
				pstmt.setInt(3, nNum);
				result = pstmt.executeUpdate();
				System.out.println(result==SUCCESS? "글수정성공":"글수정실패");
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
		// 글 삭제하기(nNum로 글 삭제하기)
		public int delete(int nNum) {
			int result = FAIL;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM NOTICE WHERE NNUM=?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nNum);
				result = pstmt.executeUpdate();
				System.out.println(result==SUCCESS? "글삭제성공":"글삭제실패");
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
}
