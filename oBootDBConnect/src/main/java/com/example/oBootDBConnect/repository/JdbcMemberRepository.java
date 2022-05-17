package com.example.oBootDBConnect.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.example.oBootDBConnect.dto.Member1;

@Repository
public class JdbcMemberRepository implements MemberRepository {
	
	private final DataSource dataSource;
	
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}
	
	@Override
	public Member1 save(Member1 member1) {
		String sql = "insert into member(id,name) values (member_seq.nextval,?)";
		System.out.println("JdbcMemberRepository sql->"+sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member1.getName());
			pstmt.executeUpdate();
			System.out.println("JdbcMemberRepository pstmt.executeUpdate After");
			return member1;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) close(conn);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	private void close(Connection conn) {
		DataSourceUtils.releaseConnection(conn, dataSource);
	}

	@Override
	public List<Member1> findAll() {
		String sql="select * from member";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Member1> members = new ArrayList<>();
			while(rs.next()) {
				Member1 member = new Member1();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				members.add(member);
			}
			return members;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				if(rs != null) rs.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

}
