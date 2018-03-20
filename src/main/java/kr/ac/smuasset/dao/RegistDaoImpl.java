package kr.ac.smuasset.dao;

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

import kr.ac.smuasset.dto.RegistDto;

public class RegistDaoImpl implements RegistDao{
	
	private JdbcTemplate jdbc;
	public RegistDaoImpl(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public int insert(final RegistDto registDto) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement statement = conn.prepareStatement(
									"insert into members(name, email, password) values(?,?,?)", new String[] {"id"});
				statement.setString(1, registDto.getName());
				statement.setString(2, registDto.getEmail());
				statement.setString(3, registDto.getPassword());
				
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
	public List<RegistDto> select(String email) {
		List<RegistDto> result = jdbc.query(
				"select * from members where email=?",
				new Object[] {email},
				new RowMapper<RegistDto>() {
					@Override
					public RegistDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						RegistDto registDto = new RegistDto();
						registDto.setName(rs.getString("name"));
						registDto.setEmail(rs.getString("email"));
						registDto.setPassword(rs.getString("password"));
						return registDto;
					}
					
				}			
				);
		return result;
	}

	@Override
	public boolean loginCheck(String email, String password) {
		
		int result = 0;
		result = jdbc.queryForInt(
				"select count(*) from members where email=? and password=?",
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
