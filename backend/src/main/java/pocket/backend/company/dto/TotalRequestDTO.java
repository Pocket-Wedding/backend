package pocket.backend.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@Getter
public class TotalRequestDTO {

    @NotBlank
    private String categoryName;

    @NotBlank
    private String locationName;

}
