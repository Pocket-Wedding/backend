package pocket.backend.hall.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.dto.HallRequest;
import pocket.backend.hall.service.HallService;

import java.util.List;

@Tag(name = "Hall", description = "웨딩홀 정보 관리")
@RestController
@RequestMapping("/api/v1/halls")
@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
public class HallController {

    private final HallService hallService;

    // WeddingHall 정보 생성
    @PostMapping("/register")
    public ResponseEntity<Hall> createHall(@RequestBody HallRequest hallRequest) {
        Hall hall = hallService.createHall(hallRequest);
        return ResponseEntity.ok(hall);
    }

    // WeddingHall 특정 정보 조회
    @GetMapping("/show/{id}")
    public ResponseEntity<Hall> getHall(@PathVariable Long id) {
        Hall hall = hallService.getHall(id);
        return ResponseEntity.ok(hall);
    }

    // WeddingHall 전체 정보 조회
    @GetMapping("/show")
    public ResponseEntity<List<Hall>> getAllHalls() {
        List<Hall> halls = hallService.getAllHalls();
        return ResponseEntity.ok(halls);
    }

    // WeddingHall 정보 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<Hall> updateHall(@PathVariable Long id, @RequestBody HallRequest hallRequest) {
        Hall hall = hallService.updateHall(id, hallRequest);
        return ResponseEntity.ok(hall);
    }

    // WeddingHall 정보 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }
}
