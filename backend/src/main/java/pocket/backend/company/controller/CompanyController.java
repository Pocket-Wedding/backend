package pocket.backend.company.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.company.domain.Company;
import pocket.backend.company.dto.CategoryRequestDTO;
import pocket.backend.company.dto.CompanyRequest;
import pocket.backend.company.dto.LocationRequestDTO;
import pocket.backend.company.dto.TotalRequestDTO;
import pocket.backend.company.service.CompanyService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@RequestMapping("/api/v1/company")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    // 모든 업체 조회
    @GetMapping("/list")
    public ResponseEntity <Optional<List<Company>>> getAllCompany() {
        return ResponseEntity.ok(companyService.findAllCompanies());
    }

    // 특정 지역에 존재하는 모든 업체 조회
    @GetMapping("/list")
    public ResponseEntity <Optional<List<Company>>> getAllCompanyByLocation(@RequestParam LocationRequestDTO locationRequestDTO) {
        return ResponseEntity.ok(companyService.findAllCompaniesByLocationId(locationRequestDTO));
    }

    // 특정 카테고리에 존재하는 모든 업체 조회
    @GetMapping("/list")
    public ResponseEntity <Optional<List<Company>>> getAllCompanyByCategory(@RequestParam CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.ok(companyService.findAllCompaniesByCategoryId(categoryRequestDTO));
    }

    // 특정 지역에 존재하는 특정 카테고리 업체 조회
    @GetMapping("/list")
    public ResponseEntity <Optional<List<Company>>> getAllStudioByLocation(@RequestParam TotalRequestDTO totalRequestDTO) {
        return ResponseEntity.ok(companyService.findAllCompaniesByLocationIdAndCategoryId(totalRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        companyService.registerCompany(companyRequest);
        return ResponseEntity.noContent().build();
    }
}