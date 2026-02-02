package org.com.ex.repository;

import org.com.ex.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DemoRepository extends JpaRepository<MovieEntity, Long> {
    Page<MovieEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<MovieEntity> findByNameContainingIgnoreCase(String name);
}

