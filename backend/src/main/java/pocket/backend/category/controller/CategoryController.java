package pocket.backend.category.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.category.dto.CategoryDeleteRequest;
import pocket.backend.category.dto.CategoryRegisterRequest;
import pocket.backend.category.dto.CategoryResponse;
import pocket.backend.category.dto.CategoryUpdateRequest;
import pocket.backend.category.service.CategoryService;

import java.util.List;

@Tag(name = "Category", description = "카테고리 정보 관리")
@RestController
@RequiredArgsConstructor(access=lombok.AccessLevel.PUBLIC)
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "특정 카테고리에 속하는 업체의 갯수를 반환하는 API")
    @GetMapping("/count")
    public ResponseEntity<Long> countCategory(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.countCategory(name));
    }

    @Operation(summary = "모든 카테고리의 정보를 반환하는 API")
    @GetMapping("")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(summary = "카테고리를 등록하는 API")
    @PostMapping("/register")
    public ResponseEntity<Void> registerCategory(@RequestBody @Valid CategoryRegisterRequest categoryRegisterRequest) {
        categoryService.registerCategory(categoryRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "카테고리를 수정하는 API")
    @PutMapping("/update")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        categoryService.updateCategory(categoryUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "카테고리를 삭제하는 API")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCategory(@RequestBody CategoryDeleteRequest categoryDeleteRequest) {
        categoryService.deleteCategory(categoryDeleteRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
