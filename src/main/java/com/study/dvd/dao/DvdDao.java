package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Dvd;
import com.study.dvd.util.DBConnectionMgr;

public class DvdDao {
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance(); //생활코딩
	// 전역 클래스내에서 사용가능
	public static List<Dvd> searchDvdByTitle(String searchText) {
		List<Dvd> dvds = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // select일때
		
		try {
			con = pool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from dvd_view ");
			sql.append("where title like ? limit 0, 50"); // \"%?%\" 안에 ?는 "" 처줌
			pstmt = con.prepareStatement(sql.toString()); // pstmt 처리하라면 sql 필요
			pstmt.setString(1, "%" + searchText + "%"); // 1번째 ?에 searchText의 값을 넣어줌
			rs = pstmt.executeQuery(); // 쿼리 실행
			
			while(rs.next()) {// 열의 번호 나열 (next가 끝날때 까지)
				Dvd dvd = Dvd.builder()
						.dvdId(rs.getInt(1))
						.registrationNumber(rs.getString(2))
						.title(rs.getString(3))
						.producerId(rs.getInt(4))
						.producerName(rs.getString(5))
						.publisherId(rs.getInt(6))
						.publisherName(rs.getString(7))
						.publicationYear(rs.getInt(8))
						.databaseDate(rs.getDate(9).toLocalDate())
						.build();
				
				dvds.add(dvd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con , pstmt , rs);
		}
		
		return dvds;
	}

}
