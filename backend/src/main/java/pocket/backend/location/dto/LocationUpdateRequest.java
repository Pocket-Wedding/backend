package pocket.backend.location.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LocationUpdateRequest {
    @NotBlank
    String prevName;

    @NotBlank
    String newName;
}
