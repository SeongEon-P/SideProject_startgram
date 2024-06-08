package org.zerock.startgram.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.startgram.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    // @EntityGraph 함께 가지고올 데이터 설정
    @EntityGraph(attributePaths = "roleSet")
    @Query("SELECT m FROM Member m WHERE m.mid = :mid and m.social = false")
    Optional<Member> getWithRoles(String mid);

    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.mpw =:mpw WHERE m.mid =:mid")
    void updatePassword(@Param("mpw") String password, @Param("mid") String mid);
}
