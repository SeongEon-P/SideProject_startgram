package org.zerock.startgram.service;

import org.zerock.startgram.DTO.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception {}
    void join(MemberJoinDTO member) throws MidExistException;
}
