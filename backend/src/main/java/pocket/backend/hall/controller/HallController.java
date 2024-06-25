package pocket.backend.hall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.dto.HallRegisterRequestDTO;
import pocket.backend.hall.dto.HallUpdateRequestDTO;
import pocket.backend.hall.service.HallService;

import java.util.List;

@Tag(name = "Hall", description = "웨딩홀 정보 관리")
@RestController
@RequestMapping("/api/v1/halls")
@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
public class HallController {

    private final HallService hallService;

    @Operation(summary = "웨딩홀을 등록하는 API")
    @PostMapping("/register")
    public ResponseEntity<Void> registerHall(@RequestBody @Valid HallRegisterRequestDTO hallRegisterRequestDTO) {
        hallService.registerHall(hallRegisterRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
    public ResponseEntity<Hall> updateHall(@PathVariable Long id, @RequestBody HallUpdateRequestDTO hallUpdate) {
        Hall hall = hallService.updateHall(id, hallUpdate);
        return ResponseEntity.ok(hall);
    }

    // WeddingHall 정보 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }
}
