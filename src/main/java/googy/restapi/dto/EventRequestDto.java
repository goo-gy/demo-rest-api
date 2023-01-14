package googy.restapi.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class EventRequestDto {
    @NotEmpty
    private String name;
    @NotNull
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime closeEventDateTime;
    private String location;
    @Min(value = 0)
    private Long basePrice;
    @Min(value = 0)
    private Long maxPrice;
    @Min(value = 0)
    private Long limitOfEnrollment;
}
