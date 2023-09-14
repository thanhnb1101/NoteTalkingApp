package net.notetalking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.notetalking.model.Note;

@Repository("noteRepository")
public interface NoteRepository extends JpaRepository<Note, Long> {
	List<Note> findAllByUserId(long userId);
}
