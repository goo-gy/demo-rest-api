package googy.restapi.dto;

import lombok.*;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class EventRequestDto {
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
}
