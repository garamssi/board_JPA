package com.garam.boardserver.member.service;

import com.garam.boardserver.member.dto.MemberDTO;

public interface MemberService {

	void register(MemberDTO userProfile);

	MemberDTO login(String id, String password);

	boolean isDuplicatedId(String id);

	MemberDTO getUserInfo(String userId);

	void updatePassword(String id, String beforePassword, String afterPassword);

	void deleteId(String id, String password);
}
