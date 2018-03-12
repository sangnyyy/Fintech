package kr.ac.smuasset.dao;

import java.util.List;

public interface RegistDao {
	public int insert(MemberRegistRequest memRegReq);
	public int count();
	public List<MemberRegistRequest> select(String email);
	public boolean loginCheck(String email, String password);

}
