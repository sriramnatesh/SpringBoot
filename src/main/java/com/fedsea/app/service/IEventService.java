package com.fedsea.app.service;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.Event;

@Service
public interface IEventService {

	void addEvent(Event event);

}
