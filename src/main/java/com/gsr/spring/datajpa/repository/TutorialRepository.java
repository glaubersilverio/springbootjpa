package com.gsr.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gsr.spring.datajpa.model.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long>{
	
	List<Tutorial> findByTitleContaining(String title);
	List<Tutorial> findByPublished(boolean published);
}
