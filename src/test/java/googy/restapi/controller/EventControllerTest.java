package googy.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import googy.restapi.domain.Event;
import googy.restapi.dto.EventRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createEvent() throws Exception {
        EventRequestDto event = EventRequestDto.builder()
                .name("Spring")
                .description("REST API TEST")
                .beginEnrollmentDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .closeEnrollmentDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .beginEventDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .closeEventDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .location("seoul")
                .basePrice(100L)
                .maxPrice(200L)
                .limitOfEnrollment(100L)
                .build();

        mockMvc.perform(post("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists());
    }

    @Test
    public void badRequestOnAdditionalField() throws Exception {
        Event event = Event.builder()
                .id(100L)
                .name("Spring")
                .description("REST API TEST")
                .beginEnrollmentDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .closeEnrollmentDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .beginEventDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .closeEventDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .location("seoul")
                .basePrice(100L)
                .maxPrice(200L)
                .limitOfEnrollment(100L)
                .free(true)
                .offline(true)
                .build();

        mockMvc.perform(post("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void badRequestOnEmpty() throws Exception {
            EventRequestDto event = EventRequestDto.builder()
                    .name("Spring")
                .build();

        mockMvc.perform(post("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void badRequestOnMinusPrice() throws Exception {
        EventRequestDto event = EventRequestDto.builder()
                .name("Spring")
                .description("REST API TEST")
                .beginEnrollmentDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .closeEnrollmentDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .beginEventDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .closeEventDateTime(LocalDateTime.of(2023, 1, 1, 1, 1, 1))
                .location("seoul")
                .basePrice(-1L)
                .maxPrice(200L)
                .limitOfEnrollment(100L)
                .build();

        mockMvc.perform(post("/api/v1/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}