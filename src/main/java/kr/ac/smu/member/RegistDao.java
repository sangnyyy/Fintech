package kr.ac.smu.member;

import java.util.List;

public interface RegistDao {
	public int insert(MemberRegistRequest memRegReq);
	public int count();
	public List<MemberRegistRequest> select();
	public boolean loginCheck(String email, String password);

}
