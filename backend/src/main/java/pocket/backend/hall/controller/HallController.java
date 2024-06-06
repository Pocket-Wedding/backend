package pocket.backend.hall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.dto.HallRequest;
import pocket.backend.hall.service.HallService;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @PostMapping
    public ResponseEntity<Hall> createHall(@RequestBody HallRequest hallRequest) {
        Hall hall = hallService.createHall(hallRequest);
        return ResponseEntity.ok(hall);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> getHall(@PathVariable Long id) {
        Hall hall = hallService.getHall(id);
        return ResponseEntity.ok(hall);
    }

    @GetMapping
    public ResponseEntity<List<Hall>> getAllHalls() {
        List<Hall> halls = hallService.getAllHalls();
        return ResponseEntity.ok(halls);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hall> updateHall(@PathVariable Long id, @RequestBody HallRequest hallRequest) {
        Hall hall = hallService.updateHall(id, hallRequest);
        return ResponseEntity.ok(hall);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }
}
