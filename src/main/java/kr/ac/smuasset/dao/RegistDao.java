package kr.ac.smuasset.dao;

import java.util.List;

import kr.ac.smuasset.dto.RegistDto;

public interface RegistDao {
	public int insert(RegistDto registDto);
	public int count();
	public List<RegistDto> select(String email);
	public boolean loginCheck(String email, String password);

}
