package com.drive.student.Favoritejob;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteJobService {

    @Autowired
    private FavoriteJobRepository favoriteJobRepository;

    public List<Favoritejob> getFavoriteJobsByStudentId(Long studentId) {
        return favoriteJobRepository.findByStudentId(studentId);
    }

    public boolean isJobFavorite(Long studentId, Long jobId) {
        return favoriteJobRepository.existsByStudentIdAndJobId(studentId, jobId);
    }

    public void addFavoriteJob(Long studentId, Long jobId) {
        Favoritejob favoriteJob = new Favoritejob();
        favoriteJob.setStudentId(studentId);
        favoriteJob.setJobId(jobId);
        favoriteJobRepository.save(favoriteJob);
    }
    @Transactional
    public void removeFavoriteJob(Long studentId, Long jobId) {
        favoriteJobRepository.deleteByStudentIdAndJobId(studentId, jobId);
    }

    public boolean isFavorite(Long studentId, Long jobId) {

        return favoriteJobRepository.existsByStudentIdAndJobId(studentId, jobId);
    }
}