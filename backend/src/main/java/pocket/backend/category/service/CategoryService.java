package pocket.backend.category.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pocket.backend.category.domain.Category;
import pocket.backend.category.domain.CategoryRepository;
import pocket.backend.category.dto.RegisterRequest;
import pocket.backend.common.exceptions.DuplicatedException;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.common.exceptions.NotFoundException;

import java.util.Optional;

@RequiredArgsConstructor(access= AccessLevel.PUBLIC)
@Service
@Slf4j
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    // 카테고리를 등록하는 메서드
    public Optional<Category> registerCategory(RegisterRequest registerRequest) {
        if(categoryRepository.existsByName(registerRequest.getName())) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_CATEGORY_NAME);
        }
        Category category = Category.builder()
                .name(registerRequest.getName())
                .build();
        log.atDebug().log("CategoryService registerCategory : {}", category);
        return Optional.of(categoryRepository.save(category));
    }

    // 해당 카테고리의 업체가 몇개인지 확인하는 메서드
    public Long countCategory(String name) {
        Category findCategory = categoryRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
        );
        return findCategory.getTotalCount();
    }
}
