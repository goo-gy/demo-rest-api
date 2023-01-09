package googy.restapi.domain;

import lombok.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class Event {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime closeEventDateTime;
    private String location;
    private Long basePrice;
    private Long maxPrice;
    private Long limitOfEnrollment;
    private boolean offline;
    private boolean free;
    private EventStatus eventStatus;
}