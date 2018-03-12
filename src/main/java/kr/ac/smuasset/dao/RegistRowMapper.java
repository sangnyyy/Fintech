package kr.ac.smuasset.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.ac.smu.dto.RegistDto;

public class RegistRowMapper implements RowMapper<RegistDto> {

	@Override
	public RegistDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		RegistDto registDto = new RegistDto();
		registDto.setPassword(rs.getString("password"));
		registDto.setEmail(rs.getString("email"));
		registDto.setName(rs.getString("name"));
		return registDto;
	}

}
