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
		          // ���� �ڷ���
		
		List<Producer> producers = new ArrayList<>();
		// �� �⺻ ����
		Connection con = null; // ������ ���̽� ����
		PreparedStatement pstmt = null; // �����ϵ� SQL ��
		ResultSet rs = null;
		
		try {
			// String sql = "select * from producer_tb where producer_name like ? limit 0, 50";
			con = pool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from producer_tb ");
			sql.append("where producer_name like ? limit 0, 50");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchText + "%"); // ���ϵ� ī�� ���(like�ڿ� ?�� ����)
			rs = pstmt.executeQuery(); // �������� rs�� ����(����)
			
			while(rs.next())   { // rs�� ���� �࿡ ����
				Producer producer = Producer.builder()
						.producerId(rs.getInt(1)) // �ۼ��� �������� 1��° ���� ������
						.producerName(rs.getString(2)) // �ۼ��� �������� 2��° ���� ������
						.build();
				
				producers.add(producer);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con , pstmt , rs); // �۾��� �Ϸ�� �� ���� �۾��� ���� ��ü �ʱ�ȭ(�Ҹ�) 
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
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // �ڹ��� sql Statement RETURN_GENERATED_KEYS �־�� Ű���� ������
			pstmt.setString(1, producer.getProducerName());
			successCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys(); // ai�� Ű���� ������
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
