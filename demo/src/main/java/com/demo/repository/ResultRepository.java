package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.dto.ResultDTO;
import com.demo.model.Result;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    

    // @Query("SELECT new com.example.dto.ResultDTO(s.id, s.tos, s.assignment.id, s.student.id, r.obtainedMarks, r.feedback, a.maxMarks) " +
    //         "FROM Submission s " +
    //         "LEFT JOIN Result r ON s.id = r.submission.id " +
    //         "JOIN Assignment a ON s.assignment.id = a.id " +
    //         "WHERE s.assignment.id = :aId AND s.student.id = :sId")
    // List<ResultDTO> fetchResult(@Param("sId") int sId, @Param("aId") int aId);

    @Query(value = "SELECT " +
        "s.id AS submission_id, " +
        "s.tos AS submission_tos, " +
        "s.assignment_id AS assignment_id, " +
        "s.student_id AS student_id, " +
        "r.obtained_marks AS obtained_marks, " +
        "r.feedback AS feedback, " +
        "a.max_marks AS max_marks " +
        "FROM Submission s " +
        "LEFT JOIN Result r ON s.id = r.submission_id " +
        "JOIN Assignment a ON s.assignment_id = a.id " +
        "WHERE s.assignment_id = :aId AND s.student_id = :sId", nativeQuery = true)
    List<Object[]> fetchResult(int sId, int aId);
    
}