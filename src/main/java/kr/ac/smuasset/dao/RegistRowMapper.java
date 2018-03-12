package kr.ac.smuasset.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RegistRowMapper implements RowMapper<MemberRegistRequest> {

	@Override
	public MemberRegistRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberRegistRequest mem = new MemberRegistRequest();
		mem.setPassword(rs.getString("password"));
		mem.setEmail(rs.getString("email"));
		mem.setName(rs.getString("name"));
		return mem;
	}

}
