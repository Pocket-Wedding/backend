package pocket.backend.category.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pocket.backend.category.domain.Category;
import pocket.backend.category.domain.CategoryRepository;
import pocket.backend.category.dto.CategoryDeleteRequest;
import pocket.backend.category.dto.CategoryRegisterRequest;
import pocket.backend.category.dto.CategoryResponse;
import pocket.backend.category.dto.CategoryUpdateRequest;
import pocket.backend.common.exceptions.DuplicatedException;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.common.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

import static pocket.backend.common.validator.NameValidator.isValidCategoryName;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 해당 카테고리의 업체가 몇개인지 확인하는 메서드
    public Long countCategory(String name) {
        isValidCategoryName(name);
        Category findCategory = categoryRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );
        return findCategory.getTotalCount();
    }

    public Long getCategoryIdByName(String name) {
        isValidCategoryName(name);
        Category findCategory = categoryRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );
        return findCategory.getId();
    }

    public List<CategoryResponse> getAllCategories() {
        return CategoryResponse.toList(categoryRepository.findAll());
    }

    public Optional<Category> getCategoryByName(String name) {
        isValidCategoryName(name);
        if (!categoryRepository.existsByName(name)) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY);
        }
        return categoryRepository.findByName(name);
    }

    // 카테고리를 등록하는 메서드
    @Transactional
    public Optional<Category> registerCategory(CategoryRegisterRequest categoryRegisterRequest) {
        isValidCategoryName(categoryRegisterRequest.getName());

        if (categoryRepository.existsByName(categoryRegisterRequest.getName())) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_CATEGORY_NAME);
        }

        Category category = Category.builder()
                .name(categoryRegisterRequest.getName())
                .build();

        return Optional.of(categoryRepository.save(category));
    }

    @Transactional
    public void updateCategory(CategoryUpdateRequest categoryUpdateRequest) {
        Category prevCategory = categoryRepository.findByName(categoryUpdateRequest.getPrevName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );

        if (categoryRepository.existsByName(categoryUpdateRequest.getNewName())) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_CATEGORY_NAME);
        }

        prevCategory.updateCategory(categoryUpdateRequest.getNewName());
    }

    @Transactional
    public void deleteCategory(CategoryDeleteRequest categoryDeleteRequest) {
        Category findCategory = categoryRepository.findByName(categoryDeleteRequest.getName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );
        categoryRepository.delete(findCategory);
    }
}
