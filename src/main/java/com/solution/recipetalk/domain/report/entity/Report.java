package com.solution.recipetalk.domain.report.entity;


import com.solution.recipetalk.domain.common.CommonEntity;
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
public class Report extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private UserDetail reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportee_id")
    private UserDetail reportee;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "screenshot_uri")
    private String screenshotURI;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "report_state")
    private ReportState reportState;
}
