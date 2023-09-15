package net.notetalking.service;

import java.util.List;

import net.notetalking.model.Note;


public interface NoteService {
	Note create(Note note);

	Note getById(long id);
	
	List<Note> getAllByUserId();
	
	boolean delete(long id);
	
	Note update(Note note);
	
	void deleteHourly();
}
