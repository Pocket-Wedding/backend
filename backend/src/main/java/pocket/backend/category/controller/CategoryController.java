package pocket.backend.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.category.dto.CategoryRequest;
import pocket.backend.category.service.CategoryService;

@RestController
@RequiredArgsConstructor(access=lombok.AccessLevel.PUBLIC)
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/count")
    public ResponseEntity<Long> countCategory(@RequestParam CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.countCategory(categoryRequest.getName()));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.registerCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.deleteCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
