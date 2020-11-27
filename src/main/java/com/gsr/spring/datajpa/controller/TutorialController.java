package com.gsr.spring.datajpa.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gsr.spring.datajpa.model.Tutorial;
import com.gsr.spring.datajpa.service.TutorialService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	private TutorialService tutorialService;
	
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required=false) String title) {
		List<Tutorial> tutorials = this.tutorialService.getAllTutorials(title);
		return ResponseEntity.ok(tutorials);
	}
	
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getById(@PathVariable(name = "id") Long id) {
		Tutorial tutorial = this.tutorialService.findById(id);
		return ResponseEntity.ok(tutorial);
	}
	
	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		Tutorial _tutorial = this.tutorialService.createTutorial(tutorial);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(_tutorial.getId()).toUri();
		return ResponseEntity.created(uri).body(_tutorial);
	}
	
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@RequestBody Tutorial tutorial, @PathVariable(name="id") Long id) {
		Tutorial _tutorial = this.tutorialService.updateTutorial(tutorial, id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(_tutorial);
	}
	
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable(name="id") Long id){
		this.tutorialService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorial(){
		this.tutorialService.deleteAllTutorials();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		List<Tutorial> tutorials = this.tutorialService.findByPublished();
		return ResponseEntity.ok(tutorials);
	}
	
}
