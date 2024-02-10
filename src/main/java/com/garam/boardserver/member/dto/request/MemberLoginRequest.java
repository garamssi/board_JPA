package com.garam.boardserver.member.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class MemberLoginRequest {
    @NonNull
    private String userId;
    @NonNull
    private String password;
}
