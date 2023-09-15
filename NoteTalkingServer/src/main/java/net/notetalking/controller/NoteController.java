package net.notetalking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.notetalking.model.Note;
import net.notetalking.service.NoteService;

@Controller
@RequestMapping(value = "/note")
public class NoteController extends BaseController {
	@Autowired
	NoteService noteService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Note> create(@RequestBody Note request) {
		Note response = noteService.create(request);
		return new ResponseEntity<Note>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity delete(@RequestParam long id) {
		return response(noteService.delete(id));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity list() throws Exception {
		List<Note> list = noteService.getAllByUserId();
		return responseListNoPaging(list);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Note> update(@RequestBody Note request) {
		Note response = noteService.update(request);
		return new ResponseEntity<Note>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ResponseEntity detail(@RequestParam long id) throws Exception {
		Note response = noteService.getById(id);
		return new ResponseEntity<Note>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/cron", method = RequestMethod.GET)
	public void testDelExl10() {
		noteService.deleteHourly();
	}
}
