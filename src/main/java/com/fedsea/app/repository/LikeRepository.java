package com.fedsea.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

	int countByReactionTypeAndPostId(String string, long postId);

	@Query(value = "select count(*) from likes where post_id = ?1 and reaction_type != 'unlike'", nativeQuery = true)
	int countByPostId(long postId);

	Like findByUserIdAndPostIdAndReactionType(Long userId, Long postId, String string);

	Like findByUserIdAndPostId(Long userId, long postId);
}
