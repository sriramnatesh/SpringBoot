package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.Feed;

@Service
public interface FeedRepository extends JpaRepository<Feed, Long> {

	List<Feed> findByUserId(Long userId);

}
