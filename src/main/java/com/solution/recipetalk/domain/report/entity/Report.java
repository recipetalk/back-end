package com.solution.recipetalk.domain.report.entity;


import com.solution.recipetalk.domain.common.AuditingEntity;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "report")
public class Report extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private UserDetail reporter;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "report_state")
    private ReportState reportState;
}
