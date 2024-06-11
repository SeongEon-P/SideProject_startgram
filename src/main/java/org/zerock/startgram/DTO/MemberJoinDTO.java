package org.zerock.startgram.DTO;

import lombok.Data;

@Data
public class MemberJoinDTO {

    private String mid;
    private String mpw;
    private String email;
    private String del;
    private String social;
    //여기서 del이 어떤역할인지 주석 한 번 달아줘요 형
    
}
