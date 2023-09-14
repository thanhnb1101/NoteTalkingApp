package net.notetalking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.notetalking.model.Note;
import net.notetalking.service.NoteService;

@Controller
public class NoteController {
	@Autowired
	NoteService noteService;
	
	@RequestMapping(value = "/note/create", method = RequestMethod.POST)
	public ResponseEntity<Note> create(@RequestBody Note request) {
		Note response = noteService.save(request);
		return new ResponseEntity<Note>(response, HttpStatus.OK);
	}
}
