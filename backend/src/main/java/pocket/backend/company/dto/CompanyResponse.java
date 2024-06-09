package pocket.backend.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pocket.backend.company.domain.Company;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CompanyResponse {
    private Long id;
    private String name;
    private String describe;
    private String address;
    private String phoneNumber;
    private Integer price;
    private String imageUrl;

    public static List<CompanyResponse> listOf(List<Company> filteredCompanies) {
        return filteredCompanies.stream()
                .map(CompanyResponse::of)
                .toList();
    }

    private static CompanyResponse of(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getDescribe(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getPrice(),
                company.getImageUrl()
        );
    }
}
