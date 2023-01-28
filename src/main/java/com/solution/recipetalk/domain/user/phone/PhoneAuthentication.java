package com.solution.recipetalk.domain.user.phone;

import com.solution.recipetalk.domain.common.AuditingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table
public class PhoneAuthentication extends AuditingEntity {
    @Id
    @Column(name = "phone_num", nullable = false)
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})")
    private String phoneNum;

    @Column(name = "is_authentication", columnDefinition = "boolean default false")
    private Boolean isAuthentication;

    @Column(name = "count", columnDefinition = "TINYINT default 0")
    private Integer count;

    @Column(name = "auth_num")
    private String authNum;

    @PrePersist
    protected void prePersist(){
        isAuthentication = false;
        count = 0;
    }

    public void increaseCount() {
        count ++;
    }

    public void updateAuthNum(String authNum){
        this.authNum = authNum;
    }

}
