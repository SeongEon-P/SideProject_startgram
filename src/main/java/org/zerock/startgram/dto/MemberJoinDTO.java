package org.zerock.startgram.dto;

import lombok.Data;

@Data
public class MemberJoinDTO {
    private String mid;
    private String mpw;
    private String mname;
    private String email;
    private String address;
//    private boolean del;
//    private boolean social;
}
