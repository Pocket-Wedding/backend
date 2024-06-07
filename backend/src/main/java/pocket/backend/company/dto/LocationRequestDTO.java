package pocket.backend.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LocationRequestDTO {

    @NotBlank
    private String locationName;
}
