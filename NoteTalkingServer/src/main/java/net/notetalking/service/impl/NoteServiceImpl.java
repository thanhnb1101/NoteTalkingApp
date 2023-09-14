package net.notetalking.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.notetalking.model.Note;
import net.notetalking.repository.NoteRepository;
import net.notetalking.repository.UserRepository;
import net.notetalking.service.NoteService;
import net.notetalking.util.ClaimPrincipal;
import net.notetalking.util.ConstantUtils;

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
	public Note create(Note note) {
		ClaimPrincipal claimPrincipal = new ClaimPrincipal();
		long userId = claimPrincipal.getLoggedInUserId();
		note.setUserId(userId);
		note.setDateCreated(new Date());
		return noteRepository.save(note);
	}


	@Override
	public List<Note> getAllByUserId() {
		ClaimPrincipal claimPrincipal = new ClaimPrincipal();
		long userId = claimPrincipal.getLoggedInUserId();
		return noteRepository.findAllByUserId(userId);
	}


	@Override
	public boolean delete(long id) {
		Note checkExist = noteRepository.findById(id).orElse(null);
		if (checkExist != null) {
			noteRepository.delete(checkExist);
			return true;
		} else {
			return false;
		}
	}


	@Override
	public Note update(Note note) {
		Note oldNote = noteRepository.findById(note.getId()).orElse(null);
		if (oldNote == null) {
			return null;
		} else {
			oldNote.setTitle(note.getTitle());
			oldNote.setContent(note.getContent());
			return noteRepository.save(oldNote);
		}
	}

}
