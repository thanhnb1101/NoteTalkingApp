package net.notetalking.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import net.notetalking.model.Note;
import net.notetalking.model.User;
import net.notetalking.repository.NoteRepository;
import net.notetalking.repository.UserRepository;
import net.notetalking.service.NoteService;
import net.notetalking.util.ClaimPrincipal;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Note getById(long id) {
		return noteRepository.findById(id).orElse(null);
	}


	@Override
	public Note save(Note note) {
		ClaimPrincipal claimPrincipal = new ClaimPrincipal();
		long userId = claimPrincipal.getLoggedInUserId();
		note.setUserId(userId);
		note.setDateCreated(new Date());
		return noteRepository.save(note);
	}


	@Override
	public List<Note> getAllByUserId(long userId) {
		return noteRepository.findAllByUserId(userId);
	}

}
