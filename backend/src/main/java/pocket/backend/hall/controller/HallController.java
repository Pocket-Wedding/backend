package pocket.backend.hall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.dto.HallDeleteRequestDTO;
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

    @Operation(summary = "모든 특정 웨딩홀의 정보를 반환하는 API")
    @GetMapping("/show/{id}")
    public ResponseEntity<Hall> getHall(@PathVariable Long id) {
        Hall hall = hallService.getHall(id);
        return ResponseEntity.ok(hall);
    }

    @Operation(summary = "모든 웨딩홀의 리스트를 반환하는 API")
    @GetMapping("/show")
    public ResponseEntity<List<Hall>> getAllHalls() {
        List<Hall> halls = hallService.getAllHalls();
        return ResponseEntity.ok(halls);
    }

    @Operation(summary = "웨딩홀을 수정하는 API")
    @PutMapping("/update")
    public ResponseEntity<Void> updateHall(@RequestBody @Valid HallUpdateRequestDTO hallUpdateRequestDTO) {
        hallService.updateHall(hallUpdateRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "웨딩홀을 삭제하는 API")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteHall(@RequestBody @Valid HallDeleteRequestDTO hallDeleteRequestDTO) {
        hallService.deleteHall(hallDeleteRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
