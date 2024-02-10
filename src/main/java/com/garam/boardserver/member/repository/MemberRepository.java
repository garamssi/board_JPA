package com.garam.boardserver.member.repository;

import com.garam.boardserver.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByUserIdAndPassword(String userId, String password);

	Member findByUserId(String id);
}
