package pocket.backend.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TotalRequestDTO {

    @NotBlank
    private String categoryName;

    @NotBlank
    private String locationName;

}
