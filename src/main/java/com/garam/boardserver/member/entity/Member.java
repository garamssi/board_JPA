package com.garam.boardserver.member.entity;


import com.garam.boardserver.member.dto.MemberDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	public enum Status {
		DEFAULT, ADMIN, DELETED
	}

	@Id @GeneratedValue
	private Long id;

	private String userId;
	private String password;
	private String nickName;
	private boolean isAdmin;
	private LocalDateTime createTime;
	private boolean isWithDraw;
	private Status status;
	private LocalDateTime updateTime;

	public Member(MemberDTO memberDTO) {
		this.id = memberDTO.getId();
		this.userId = memberDTO.getUserId();
		this.password = memberDTO.getPassword();
		this.nickName = memberDTO.getNickName();
		this.isAdmin = memberDTO.isAdmin();
		this.createTime = memberDTO.getCreateTime();
		this.isWithDraw = memberDTO.isWithDraw();
		this.status = memberDTO.getStatus();
		this.updateTime = memberDTO.getUpdateTime();
	}

}
