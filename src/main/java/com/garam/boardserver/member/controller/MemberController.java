package com.garam.boardserver.member.controller;

import com.garam.boardserver.member.dto.MemberDTO;
import com.garam.boardserver.member.dto.request.MemberDeleteId;
import com.garam.boardserver.member.dto.request.MemberLoginRequest;
import com.garam.boardserver.member.dto.request.MemberUpdatePasswordRequest;
import com.garam.boardserver.member.dto.response.LoginResponse;
import com.garam.boardserver.member.dto.response.UserInfoResponse;
import com.garam.boardserver.enums.Status;
import com.garam.boardserver.member.service.MemberService;
import com.garam.boardserver.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

	private final MemberService memberService;
	private static final ResponseEntity<LoginResponse> FAIL_RESPONSE = new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST);
	private static LoginResponse loginResponse;

	@PostMapping("sign-up")
	@ResponseStatus(HttpStatus.CREATED)
	public void signUp(@RequestBody MemberDTO memberDTO) {
		if (MemberDTO.hasNullDataBeforeSignup(memberDTO)) {
			throw new NullPointerException("회원가입시 필수 데이터를 모두 입력해야 합니다.");
		}
		memberService.register(memberDTO);
	}

	@PostMapping("sign-in")
	public HttpStatus login(@RequestBody MemberLoginRequest loginRequest,
	                        HttpSession session) {
		ResponseEntity<LoginResponse> responseEntity = null;
		String id = loginRequest.getUserId();
		String password = loginRequest.getPassword();
		MemberDTO userInfo = memberService.login(id, password);

		if (userInfo == null) {
			return HttpStatus.NOT_FOUND;
		} else if (userInfo != null) {
			loginResponse = LoginResponse.success(userInfo);
			if (userInfo.getStatus().equals(Status.ADMIN))
				SessionUtil.setLoginAdminId(session, id);
			else
				SessionUtil.setLoginMemberId(session, id);

			responseEntity = new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
		} else {
			throw new RuntimeException("Login Error! 유저 정보가 없거나 지워진 유저 정보입니다.");
		}

		return HttpStatus.OK;
	}

	@GetMapping("my-info")
	public UserInfoResponse memberInfo(HttpSession session) {
		String id = SessionUtil.getLoginMemberId(session);
		if (id == null) id = SessionUtil.getLoginAdminId(session);
		MemberDTO memberInfo = memberService.getUserInfo(id);
		return new UserInfoResponse(memberInfo);
	}

	@PutMapping("logout")
	public void logout(String accountId, HttpSession session) {
		SessionUtil.clear(session);
	}

	@PatchMapping("password")
	public ResponseEntity<LoginResponse> updateUserPassword(@RequestBody MemberUpdatePasswordRequest memberUpdatePasswordRequest,
	                                                        HttpSession session) {
		ResponseEntity<LoginResponse> responseEntity = null;
		String Id = SessionUtil.getLoginMemberId(session);
		String beforePassword = memberUpdatePasswordRequest.getBeforePassword();
		String afterPassword = memberUpdatePasswordRequest.getAfterPassword();

		try {
			memberService.updatePassword(Id, beforePassword, afterPassword);
			ResponseEntity.ok(new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK));
		} catch (IllegalArgumentException e) {
			log.error("updatePassword 실패", e);
			responseEntity = FAIL_RESPONSE;
		}
		return responseEntity;
	}

	@DeleteMapping
	public ResponseEntity<LoginResponse> deleteId(@RequestBody MemberDeleteId memberDeleteId,
	                                              HttpSession session) {
		ResponseEntity<LoginResponse> responseEntity = null;
		String Id = SessionUtil.getLoginMemberId(session);

		try {
			memberService.deleteId(Id, memberDeleteId.getPassword());
			responseEntity = new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
		} catch (RuntimeException e) {
			log.info("deleteID 실패");
			responseEntity = FAIL_RESPONSE;
		}
		return responseEntity;
	}
}
