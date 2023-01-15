package googy.restapi.service;

import googy.restapi.domain.Event;
import googy.restapi.dto.EventRequestDto;
import googy.restapi.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    EventRepository eventRepository;
    ModelMapper modelMapper;

    @Autowired
    public EventService(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    public Event createEvent(EventRequestDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        return eventRepository.save(event);
    }
}
