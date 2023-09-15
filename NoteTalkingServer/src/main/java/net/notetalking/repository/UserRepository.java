package net.notetalking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.notetalking.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
	
	@Query(value = "SELECT id FROM user", nativeQuery = true)
	List<Long> findAllUserIds();
}
