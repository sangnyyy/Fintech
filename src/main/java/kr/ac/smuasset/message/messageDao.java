package kr.ac.smuasset.message;

import java.util.List;

import kr.ac.smuasset.member.MemberRegistRequest;

public interface messageDao {
	public int insert();
	public int count();
	//public List<MemberRegistRequest> select(String email);

}
