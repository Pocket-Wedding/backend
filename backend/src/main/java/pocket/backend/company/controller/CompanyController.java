package pocket.backend.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.company.dto.CompanyDeleteRequestDTO;
import pocket.backend.company.dto.CompanyRegisterRequestDTO;
import pocket.backend.company.dto.CompanyResponse;
import pocket.backend.company.dto.CompanyUpdateRequestDTO;
import pocket.backend.company.service.CompanyService;

import java.util.List;

@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@RequestMapping("/api/v1/company")
@RestController
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "모든 업체 조회 API")
    @GetMapping("/list")
    public ResponseEntity<List<CompanyResponse>> getAllCompany() {
        return ResponseEntity.ok(companyService.findAllCompanies());
    }

    @Operation(summary = "특정 위치에 존재하는 모든 업체 조회 API")
    @GetMapping("/list/location")
    public ResponseEntity<List<CompanyResponse>> getAllCompanyByLocation(@RequestParam String locationName) {

        List<CompanyResponse> companies = companyService.findAllCompaniesByLocationId(locationName);
        return ResponseEntity.ok(companies);
    }

    @Operation(summary = "특정 카테고리에 속하는 모든 업체 조회 API")
    @GetMapping("/list/category")
    public ResponseEntity<List<CompanyResponse>> getAllCompanyByCategory(@RequestParam String categoryName) {
        return ResponseEntity.ok(companyService.findAllCompaniesByCategoryId(categoryName));
    }

    @Operation(summary = "특정 위치와 특정 카테고리에 속하는 모든 업체 조회 API")
    @GetMapping("/list/total")
    public ResponseEntity<List<CompanyResponse>> getAllCompanyByLocationAndCategory(@RequestParam String locationName, @RequestParam String categoryName) {
        return ResponseEntity.ok(companyService.findAllCompaniesByLocationIdAndCategoryId(locationName, categoryName));
    }

    @Operation(summary = "업체 등록 API")
    @PostMapping("/register")
    public ResponseEntity<Void> registerCompany(@RequestBody @Valid CompanyRegisterRequestDTO companyRegisterRequestDTO) {
        companyService.registerCompany(companyRegisterRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "업체 수정 API")
    @PutMapping("/update")
    public ResponseEntity<Void> updateCompany(@RequestBody @Valid CompanyUpdateRequestDTO companyUpdateRequestDTO) {
        companyService.updateCompany(companyUpdateRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "업체 삭제 API")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCompany(@RequestBody @Valid CompanyDeleteRequestDTO companyDeleteRequestDTO) {
        companyService.deleteCompany(companyDeleteRequestDTO);
        return ResponseEntity.noContent().build();
    }
}