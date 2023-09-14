package net.notetalking.service;

import java.util.List;

import net.notetalking.model.Note;


public interface NoteService {
	Note save(Note note);

	Note getById(long id);
	
	List<Note> getAllByUserId(long userId);
}
