package com.solution.recipetalk.domain.user.phone.entity;

import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.exception.signup.PhoneAuthNotEqualException;
import com.solution.recipetalk.exception.signup.AuthRequestTimeoutException;
import com.solution.recipetalk.exception.signup.VerifiedException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


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

    public void authComplete() {
        isAuthentication = true;
    }

    public void isValid(){
        if(count == null){
            return;
        }

        if ( count > 5 ){
            throw new PhoneAuthNotEqualException();
        }
        if(isAuthentication != null && isAuthentication){
            throw new VerifiedException();
        }
    }

    public void verifyHandlerWithLastModifiedDate() {
        if (!getModifiedDate().isAfter(LocalDateTime.now().minusMonths(4))) {
            throw new AuthRequestTimeoutException();
        }
    }

}
