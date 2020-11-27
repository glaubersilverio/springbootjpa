package com.gsr.spring.datajpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsr.spring.datajpa.exception.ObjectNotFoundException;
import com.gsr.spring.datajpa.model.Tutorial;
import com.gsr.spring.datajpa.repository.TutorialRepository;

@Service
public class TutorialService {

	@Autowired
	private TutorialRepository tutorialRepository;

	public List<Tutorial> getAllTutorials(String title) {
		List<Tutorial> tutorials = title == null ? this.tutorialRepository.findAll()
				: this.tutorialRepository.findByTitleContaining(title);
		return tutorials;
	}

	public Tutorial findById(Long id) {
		Optional<Tutorial> tutorial = this.tutorialRepository.findById(id);
		return tutorial.orElseThrow(
				() -> new ObjectNotFoundException("Object not found with id " + id + " - Class: " + Tutorial.class));
	}
	
	public Tutorial createTutorial(Tutorial tutorial) {
		tutorial.setId(null);
		return this.tutorialRepository.save(tutorial);
	}
	
	public Tutorial updateTutorial(Tutorial _tutorial, Long id) {
		Tutorial tutorial = this.findById(id);
		tutorial.setDescription(_tutorial.getDescription());
		tutorial.setPublished(_tutorial.isPublished());
		tutorial.setTitle(_tutorial.getTitle());
		this.tutorialRepository.save(tutorial);
		return tutorial;
	}
	
	public void delete(Long id) {
		this.findById(id);
		this.tutorialRepository.deleteById(id);
	}
	
	public void deleteAllTutorials() {
		this.tutorialRepository.deleteAll();
	}
	
	public List<Tutorial> findByPublished() {
		List<Tutorial> tutorials = this.tutorialRepository.findByPublished(true);
		return tutorials;
	}

}
