package com.solution.recipetalk.domain.report.repository;

import com.solution.recipetalk.domain.report.entity.Report;
import com.solution.recipetalk.dto.report.ReportResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository<Report, Long> {
    /**
     *
     * @param pageable
     * @return CommentResponseDTO
     * 댓글과 마찬가지로 사용자 삭제 여부는 DTO에서, 이미 처리가 완료된 신고 내역은 조회하지 않도록
     */
    @Query(
            value = "select new com.solution.recipetalk.dto.report.ReportResponseDTO(reporter.username, reporter.nickname, reporter.profileImageURI, r.description, r.reportState, reporter.isDeleted) from Report r join UserDetail reporter on r.reporter = reporter where r.reportState <> 3 order by r.id",
            countQuery = "select count(*) from Report r"
    )
    Page<ReportResponseDTO> findAllReports(Pageable pageable);
}
