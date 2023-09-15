package net.notetalking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.notetalking.model.Note;

@Repository("noteRepository")
public interface NoteRepository extends JpaRepository<Note, Long> {
	
	@Query(value = "SELECT * FROM note WHERE user_id = :userId ORDER BY date_created DESC LIMIT 10", nativeQuery = true)
	List<Note> getNotesByUserIdLimit(@Param("userId") String userId);
	
	@Query(value = "SELECT * FROM note WHERE user_id = :userId ORDER BY date_created DESC", nativeQuery = true)
	List<Note> getNotesByUserId(@Param("userId") String userId);

	void deleteByIdIn(List<Long> ids);
	
}
