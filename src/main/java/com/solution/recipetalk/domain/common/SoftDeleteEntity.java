package com.solution.recipetalk.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@MappedSuperclass
public abstract class SoftDeleteEntity extends AuditingEntity{
    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @PrePersist
    protected void noDeleted(){
        isDeleted = false;
    } //JPA 영속성 추가되면 default로 추가되게 함. 궂이 false 일일이 추가 안해도 됨.

}
