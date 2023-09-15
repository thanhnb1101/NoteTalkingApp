package net.notetalking.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.notetalking.model.Note;
import net.notetalking.model.User;
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
	
	private long getLoggedInUserId() {
		ClaimPrincipal claimPrincipal = new ClaimPrincipal();
		String userName = claimPrincipal.getLoggedInUserName();
		User user = userRepository.findByUserName(userName);
		return user.getId();
	}

	@Override
	public Note create(Note note) {
		note.setUserId(getLoggedInUserId());
		note.setDateCreated(new Date());
		return noteRepository.save(note);
	}

	@Override
	public List<Note> getAllByUserId() {
		return noteRepository.getNotesByUserIdLimit(String.valueOf(getLoggedInUserId()));
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

	@Override
	@Transactional
	public void deleteHourly() {
		List<Long> listUserIds = userRepository.findAllUserIds();
		List<Long> ids = new ArrayList();
		for (Long userId : listUserIds) {
			List<Note> listNote = noteRepository.getNotesByUserId(String.valueOf(userId));
			if (listNote.size() > ConstantUtils.LIMIT_RECORD) {
				for (int i = ConstantUtils.LIMIT_RECORD + 1; i < listNote.size(); i++) {
					ids.add(listNote.get(i).getId());
				}
			}
		}
		if (ids.size() > 0) {
			noteRepository.deleteByIdIn(ids);
		}
	}

}
