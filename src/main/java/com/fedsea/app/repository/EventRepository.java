package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findByUserId(Long userId);

	Event findByEventId(Long eventId);

}
