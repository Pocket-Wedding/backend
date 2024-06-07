package pocket.backend.category.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pocket.backend.category.domain.Category;
import pocket.backend.category.domain.CategoryRepository;
import pocket.backend.category.dto.CategoryRequest;
import pocket.backend.common.exceptions.DuplicatedException;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.common.exceptions.NotFoundException;

import java.util.Optional;

@RequiredArgsConstructor(access= AccessLevel.PUBLIC)
@Service
public class CategoryService{

    private final CategoryRepository categoryRepository;

    // 해당 카테고리의 업체가 몇개인지 확인하는 메서드
    public Long countCategory(String name) {
        Category findCategory = categoryRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );
        return findCategory.getTotalCount();
    }

    public Long getCategoryIdByName(String name) {
        Category findCategory = categoryRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );
        return findCategory.getId();
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    // 카테고리를 등록하는 메서드
    @Transactional
    public Optional<Category> registerCategory(CategoryRequest categoryRequest) {
        if(categoryRepository.existsByName(categoryRequest.getName())) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_CATEGORY_NAME);
        }
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .build();

        return Optional.of(categoryRepository.save(category));
    }

    @Transactional
    public void updateCategory(CategoryRequest categoryRequest) {
        Category findCategory = categoryRepository.findByName(categoryRequest.getName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );
        findCategory.updateCategory(categoryRequest.getName());
    }

    @Transactional
    public void deleteCategory(CategoryRequest categoryRequest) {
        Category findCategory = categoryRepository.findByName(categoryRequest.getName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );
        categoryRepository.delete(findCategory);
    }
}
