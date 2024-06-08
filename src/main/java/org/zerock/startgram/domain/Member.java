package org.zerock.startgram.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member extends BaseEntity {
    @Id
    private String mid;
    private String mpw;
    private String mname;
    private String email;
    private String address;


    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String mpw) {
        this.mpw = mpw;
    }
    public void changeEmail(String email) {
        this.email = email;
    }
    public void addRole(MemberRole role) {
        this.roleSet.add(role);
    }
    public void clearRoles(){
        this.roleSet.clear();
    }

}




















