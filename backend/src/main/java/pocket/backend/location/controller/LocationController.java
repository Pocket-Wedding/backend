package pocket.backend.location.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.location.dto.LocationRequest;
import pocket.backend.location.service.LocationService;

@RestController
@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@RequestMapping("/api/v1/location")
public class LocationController {
    private final LocationService locationService;

    // 해당 위치의 업체가 몇개인지 확인하는 메서드
    @GetMapping("/count")
    public ResponseEntity<Long> countLocation(@RequestParam(defaultValue="") String name) {
        return ResponseEntity.ok(locationService.countLocation(name));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerLocation(LocationRequest locationRequest) {
        locationService.registerLocation(locationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateLocation(LocationRequest locationRequest) {
        locationService.updateLocation(locationRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteLocation(LocationRequest locationRequest) {
        locationService.deleteLocation(locationRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
