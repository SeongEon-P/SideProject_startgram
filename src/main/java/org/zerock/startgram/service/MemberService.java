package org.zerock.startgram.service;

import org.zerock.startgram.DTO.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception {}
    void join(MemberJoinDTO member) throws MidExistException;
    void modify(MemberJoinDTO member);
    boolean existMember(String mid);
    MemberJoinDTO getMember(String mid);
    void remove(String mid);
//  void modify(String mpw, String mid);
}
