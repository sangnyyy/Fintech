package kr.ac.smu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


public class JdbcRegistDao implements RegistDao {
	private JdbcTemplate jdbc;
//	private RowMapper<MemberRegistRequest> registRowMapper;
	
	public JdbcRegistDao(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		jdbc = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insert(final MemberRegistRequest memRegReq) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement statement = conn.prepareStatement(
									"insert into members(name, email, password) values(?,?,?)", new String[] {"id"});
				statement.setString(1, memRegReq.getName());
				statement.setString(2, memRegReq.getEmail());
				statement.setString(3, memRegReq.getPassword());
				
				return statement;
			}
		};
		jdbc.update(psc, keyHolder);
		Number idNum = keyHolder.getKey();
		return idNum.intValue();
	}
	
	@Override
	public int count() {
		int result = jdbc.queryForInt("select count(*) from members");
		return result;
	}

	@Override
	public List<MemberRegistRequest> select(String email) {
		List<MemberRegistRequest> result = jdbc.query(
				"select * from members where email=?",
				new Object[] {email},
				new RowMapper<MemberRegistRequest>() {
					@Override
					public MemberRegistRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
						MemberRegistRequest mem = new MemberRegistRequest();
						mem.setName(rs.getString("name"));
						mem.setEmail(rs.getString("email"));
						mem.setPassword(rs.getString("password"));
						return mem;
					}
					
				}			
				);
		return result;
	}

	@Override
	public boolean loginCheck(String email, String password) {
		
		int result = 0;
		result = jdbc.queryForInt(
				"select count(email) from members where email=? and password=?",
				new Object[] {email, password}			
				);
		if(result > 0) { 
			return true;
		}
		else {
			return false;
		}
	}

}
