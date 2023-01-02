package com.solution.recipetalk.domain.bill.repository;

import com.solution.recipetalk.domain.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
