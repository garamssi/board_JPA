package com.garam.boardserver.member.dto;

import com.garam.boardserver.member.entity.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class MemberDTO {

	private Long id;

	private String userId;
	private String password;
	private String nickName;
	private boolean isAdmin;
	private LocalDateTime createTime;
	private boolean isWithDraw;
	private Member.Status status;
	private LocalDateTime updateTime;

	public MemberDTO(Member member){
		this.id = member.getId();
		this.userId = member.getUserId();
		this.password = member.getPassword();
		this.nickName = member.getNickName();
		this.isAdmin = member.isAdmin();
		this.createTime = member.getCreateTime();
		this.isWithDraw = member.isWithDraw();
		this.status = member.getStatus();
		this.updateTime = member.getUpdateTime();
	}

	public static boolean hasNullDataBeforeSignup(MemberDTO memberDTO) {
		return memberDTO.getUserId() == null || memberDTO.getPassword() == null
			|| memberDTO.getNickName() == null;
	}
}
