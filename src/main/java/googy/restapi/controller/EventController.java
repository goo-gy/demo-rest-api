package googy.restapi.controller;

import googy.restapi.domain.Event;
import googy.restapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/v1/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {
    EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event) {
        Event resultEvent = eventService.createEvent(event);
        URI createdUri = linkTo(EventController.class)
                .slash("{id}")
                .toUri();
        return ResponseEntity.created(createdUri).body(resultEvent);
    }
}
