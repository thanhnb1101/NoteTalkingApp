package net.notetalking.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.notetalking.model.Note;
import net.notetalking.model.User;
import net.notetalking.repository.NoteRepository;
import net.notetalking.repository.UserRepository;
import net.notetalking.service.NoteService;
import net.notetalking.service.UserService;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Autowired
	NoteRepository noteRepository;



	@Override
	public Note getById(long id) {
		return noteRepository.findById(id).orElse(null);
	}


	@Override
	public Note save(Note note) {
		return noteRepository.save(note);
	}


	@Override
	public List<Note> getAllByUserId(long userId) {
		return noteRepository.findAllByUserId(userId);
	}

}
