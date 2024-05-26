package pocket.backend.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
