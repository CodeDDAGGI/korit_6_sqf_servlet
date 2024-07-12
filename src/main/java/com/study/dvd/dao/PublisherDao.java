package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.study.dvd.entity.Publisher;
import com.study.dvd.util.DBConnectionMgr;

public class PublisherDao {
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance();
	
	public static int save(Publisher publisher) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = pool.getConnection();
			String sql = "insert into producer_tb values(0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // �ڹ��� sql Statement RETURN_GENERATED_KEYS �־�� Ű���� ������
			pstmt.setString(1, publisher.getPublisherName());
			successCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys(); // ai�� Ű���� ������
			while(rs.next()) {
				publisher.setPublisherId(rs.getInt(1));				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con , pstmt , rs);
		}
		
		
		return successCount;
	}
}

