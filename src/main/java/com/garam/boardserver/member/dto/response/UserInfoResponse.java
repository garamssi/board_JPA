package com.garam.boardserver.member.dto.response;

import com.garam.boardserver.member.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private MemberDTO memberDTO;
}
