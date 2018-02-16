package kr.ac.smu.message;

import java.util.List;

import kr.ac.smu.member.MemberRegistRequest;

public interface messageDao {
	public int insert();
	public int count();
	//public List<MemberRegistRequest> select(String email);

}
