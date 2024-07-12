package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Producer;
import com.study.dvd.util.DBConnectionMgr;

public class ProducerDao {
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance();

	public static List<Producer> searchProducerByProducerName(String searchText) {
		          // 리턴 자료형
		
		List<Producer> producers = new ArrayList<>();
		// ▼ 기본 세팅
		Connection con = null; // 데이터 베이스 연결
		PreparedStatement pstmt = null; // 컴파일된 SQL 문
		ResultSet rs = null;
		
		try {
			// String sql = "select * from producer_tb where producer_name like ? limit 0, 50";
			con = pool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from producer_tb ");
			sql.append("where producer_name like ? limit 0, 50");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchText + "%"); // 와일드 카드 사용(like뒤에 ?에 대입)
			rs = pstmt.executeQuery(); // 쿼리문을 rs에 대입(실행)
			
			while(rs.next())   { // rs의 다음 행에 대입
				Producer producer = Producer.builder()
						.producerId(rs.getInt(1)) // 작성된 쿼리문에 1번째 열을 가져옴
						.producerName(rs.getString(2)) // 작성된 쿼리문에 2번째 열을 가져옴
						.build();
				
				producers.add(producer);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con , pstmt , rs); // 작업이 완료된 후 다음 작업을 위해 객체 초기화(소멸) 
		}
		
		return producers;
	}
	
	public static int save(Producer producer) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = pool.getConnection();
			String sql = "insert into producer_tb values(0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // 자바의 sql Statement RETURN_GENERATED_KEYS 있어야 키값을 가져옴
			pstmt.setString(1, producer.getProducerName());
			successCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys(); // ai된 키값을 가져옴
			while(rs.next()) {
				producer.setProducerId(rs.getInt(1));				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con , pstmt , rs);
		}
		
		
		return successCount;
	}
	
	
}
