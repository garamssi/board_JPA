package com.garam.boardserver.member.service.impl;

import com.garam.boardserver.member.dto.MemberDTO;
import com.garam.boardserver.member.entity.Member;
import com.garam.boardserver.exception.DuplicateIdException;
import com.garam.boardserver.member.service.MemberService;
import com.garam.boardserver.member.repository.MemberRepository;
import com.garam.boardserver.utils.SHA256Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void register(MemberDTO memberDTO) {
		boolean dupleIdResult = isDuplicatedId(memberDTO.getUserId());
		if (dupleIdResult) {
			throw new DuplicateIdException("중복된 아이디입니다.");
		}

		Member member = new Member(memberDTO);
		member.setCreateTime(LocalDateTime.now());
		member.setPassword(SHA256Util.encryptSHA256(member.getPassword()));

		Member memberInfo = memberRepository.save(member);


		if (memberInfo == null) {
			log.error("insertMember ERROR! {}", memberDTO);
			throw new RuntimeException(
				"insertUser ERROR! 회원가입 메서드를 확인해주세요\n" + "Params : " + memberDTO);
		}
	}

	@Override
	public MemberDTO login(String id, String password) {

		String cryptoPassword = SHA256Util.encryptSHA256(password);
		Member memberInfo = memberRepository.findByUserIdAndPassword(id, cryptoPassword);
		MemberDTO memberDTO = new MemberDTO(memberInfo);
		return memberDTO;
	}

	@Override
	public boolean isDuplicatedId(String userId) {
		return memberRepository.findByUserId(userId) != null;
	}

	@Override
	public MemberDTO getUserInfo(String userId) {
		return null;
	}

	@Override
	public void updatePassword(String id, String beforePassword, String afterPassword) {
		String cryptoPassword = SHA256Util.encryptSHA256(beforePassword);
		Member memberInfo = memberRepository.findByUserIdAndPassword(id, cryptoPassword);

		if (memberInfo != null) {
			memberInfo.setPassword(SHA256Util.encryptSHA256(afterPassword));
		} else {
			log.error("updatePasswrod ERROR! {}", memberInfo);
			throw new IllegalArgumentException("updatePasswrod ERROR! 비밀번호 변경 메서드를 확인해주세요\n" + "Params : " + memberInfo);
		}

	}

	@Override
	public void deleteId(String id, String password) {
		String cryptoPassword = SHA256Util.encryptSHA256(password);
		Member memberInfo = memberRepository.findByUserIdAndPassword(id, cryptoPassword);

		if (memberInfo != null) {
			memberRepository.deleteById(memberInfo.getId());
		} else {
			log.error("deleteId ERROR! {}", memberInfo);
			throw new RuntimeException("deleteId ERROR! id 삭제 메서드를 확인해주세요\n" + "Params : " + memberInfo);
		}
	}
}
