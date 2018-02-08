package kr.ac.smu.member;

import java.util.List;

public interface RegistDao {
	public int insert(MemberRegistRequest memRegReq);
	public int count();
	public List<MemberRegistRequest> select(String email, String password);
}
