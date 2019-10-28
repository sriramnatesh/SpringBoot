package com.fedsea.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.Like;
import com.fedsea.app.model.PageLike;

@Repository
public interface PageLikeRepository extends JpaRepository<PageLike, Long> {

	int countByReactionTypeAndPageId(String string, long pageId);

	@Query(value = "select count(*) from pagelikes where page_id = ?1 and reaction_type != 'unlike'", nativeQuery = true)
	int countByPageId(long pageId);

	PageLike findByUserIdAndPageIdAndReactionType(Long userId, Long pageId, String string);

	PageLike findByUserIdAndPageId(Long userId, long pageId);
}
