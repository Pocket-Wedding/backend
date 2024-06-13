package pocket.backend.location.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class LocationUpdateRequest {
    @NotBlank
    String prevName;

    @NotBlank
    String newName;
}
