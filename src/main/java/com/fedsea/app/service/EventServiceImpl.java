package com.fedsea.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.Event;
import com.fedsea.app.repository.EventRepository;

@Service
public class EventServiceImpl implements IEventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public void addEvent(Event event) {
		eventRepository.save(event);
	}

}
