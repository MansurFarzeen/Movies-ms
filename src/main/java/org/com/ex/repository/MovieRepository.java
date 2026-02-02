package org.com.ex.repository;

import org.com.ex.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    Page<MovieEntity> findByTitleContainingIgnoreCase(Pageable pageable, String title);
}
