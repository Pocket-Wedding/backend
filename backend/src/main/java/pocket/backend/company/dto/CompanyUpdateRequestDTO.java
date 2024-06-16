package pocket.backend.company.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class CompanyUpdateRequestDTO {

    @NotNull
    @Min(0)
    private Long id;

    private String name;

    @Lob
    private String describe;

    private String address;

    //정규표현식
    @Pattern(regexp = "^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4])-\\d{3,4}|01(0-\\d{4}|[1-9]-\\d{3,4}))-\\d{4}$")
    private String phoneNumber;

    @Min(0)
    private Integer price;

    private String imageUrl;
}
