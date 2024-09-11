package com.drive.student.Favoritejob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteJobRepository extends JpaRepository<Favoritejob, Long> {
    List<Favoritejob> findByStudentId(Long studentId);
    boolean existsByStudentIdAndJobId(Long studentId, Long jobId);
    void deleteByStudentIdAndJobId(Long studentId, Long jobId);
}