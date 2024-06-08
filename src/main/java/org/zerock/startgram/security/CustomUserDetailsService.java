package org.zerock.startgram.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.startgram.domain.Member;
import org.zerock.startgram.repository.MemberRepository;
import org.zerock.startgram.security.dto.MemberSecurityDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
// 일반 로그인시의 유저 데이터 및 권한 설정입니다.
public class CustomUserDetailsService implements UserDetailsService {
  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("loadUserByUsername : "+username);
    //데이터베이스에 username으로 검색한 회원 정보를 취득
    Optional<Member> result = memberRepository.getWithRoles(username);
    //회원정보가 있는지 확인하는 if문
    if (result.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }
    //데이터가 있으면 멤버의 데이터를 저장
    Member member = result.get();
    // Member객체를 MemberSecurityDTO 객체로 변환
    MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
        member.getMid(),
        member.getMpw(),
        member.getEmail(),
        //ROLE_USER, ROLE_ADMIN
        member.getRoleSet().stream().map(memberRole ->
          new SimpleGrantedAuthority("ROLE_"+memberRole.name())
        ).collect(Collectors.toList()));
    log.info("memberSecurityDTO");
    log.info(memberSecurityDTO);
    return memberSecurityDTO;
  }
}
