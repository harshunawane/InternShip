package com.InternShip.InternShip.repository;

import com.InternShip.InternShip.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer> {
    public List<Mentor> findByNumberOfprojectMentored(Integer numberOfprojectMentored);
}
