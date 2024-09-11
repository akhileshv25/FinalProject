package com.drive.student.Favoritejob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteJobController {

    @Autowired
    private FavoriteJobService favoriteJobService;

    @GetMapping("/{studentId}/get-fav-jobs")
    public ResponseEntity<List<Favoritejob>> getFavoriteJobs(@PathVariable Long studentId) {
        List<Favoritejob> favoriteJobs = favoriteJobService.getFavoriteJobsByStudentId(studentId);
        return ResponseEntity.ok(favoriteJobs);
    }

    @PostMapping("/{studentId}/add-jobs/{jobId}")
    public ResponseEntity<Void> addFavoriteJob(@PathVariable Long studentId, @PathVariable Long jobId) {
        favoriteJobService.addFavoriteJob(studentId, jobId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{studentId}/delete-fav-job/{jobId}")
    public ResponseEntity<Void> removeFavoriteJob(@PathVariable Long studentId, @PathVariable Long jobId) {
        favoriteJobService.removeFavoriteJob(studentId, jobId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/isFavorite/{studentId}/{jobId}")
    public ResponseEntity<Boolean> isFavorite(@PathVariable Long studentId, @PathVariable Long jobId) {
        boolean isFavorite = favoriteJobService.isFavorite(studentId, jobId);
        return ResponseEntity.ok(isFavorite);
    }
}
