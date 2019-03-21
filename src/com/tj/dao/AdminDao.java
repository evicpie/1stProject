package com.tj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.dto.AdminDto;

public class AdminDao {
	private DataSource ds;
	public static final int EXISTENT = 1;
	public static final int NONEXISTENT = 0;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public AdminDao() {
		try {
			Context ctx = new InitialContext();
			ds = 
			(DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	public int aIdConfirm(String mId) {
		int result = NONEXISTENT;
		AdminDto admin = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMINID WHERE AID=?";
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXISTENT;
				admin = new AdminDto();
				admin.setaId(rs.getString("aId"));
				admin.setaPw(rs.getString("aPw"));
				admin.setaName(rs.getString("aName"));
			}else {
				result = NONEXISTENT;
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
	public AdminDto getAdmin(String aId) {
		AdminDto admin = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMINID WHERE AID=?";
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				admin = new AdminDto();
				admin.setaId(rs.getString("aId"));
				admin.setaPw(rs.getString("aPw"));
				admin.setaName(rs.getString("aName"));
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
		return admin;
	}
}
