package com.solution.recipetalk.domain.image.repository;

import com.solution.recipetalk.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
