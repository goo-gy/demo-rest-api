package googy.restapi.controller;

import googy.restapi.domain.Event;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
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
    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event) {
        URI createdUri = linkTo(EventController.class)
                .slash("{id}")
                .toUri();
//        event.setId(10L);
        return ResponseEntity.created(createdUri).body(event);
    }
}
