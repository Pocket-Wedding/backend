package pocket.backend.location.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pocket.backend.location.dto.LocationDeleteRequest;
import pocket.backend.location.dto.LocationRegisterRequest;
import pocket.backend.location.dto.LocationResponse;
import pocket.backend.location.dto.LocationUpdateRequest;
import pocket.backend.location.service.LocationService;

import java.util.List;

@Slf4j
@Tag(name = "Location", description = "위치 정보 관리")
@RestController
@RequiredArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@RequestMapping("/api/v1/location")
public class LocationController {

    private final LocationService locationService;

    @Operation(summary = "특정 위치에 속하는 업체의 갯수를 반환하는 API")
    @GetMapping("/count")
    public ResponseEntity<Long> countLocation(@RequestParam(defaultValue="") String name) {
        return ResponseEntity.ok(locationService.countLocation(name));
    }

    @Operation(summary = "모든 지역 정보를 반환하는 API")
    @GetMapping("")
    public ResponseEntity<List<LocationResponse>> getAllLocation() {
        return ResponseEntity.ok(locationService.getAllLocation());
    }

    @Operation(summary = "지역를 등록하는 API")
    @PostMapping("/register")
    public ResponseEntity<Void> registerLocation(@RequestBody @Valid LocationRegisterRequest locationRegisterRequest) {
        log.info("Registering location: {}", locationRegisterRequest);
        locationService.registerLocation(locationRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "지역를 수정하는 API")
    @PutMapping("/update")
    public ResponseEntity<Void> updateLocation(@RequestBody @Valid LocationUpdateRequest locationUpdateRequest) {
        locationService.updateLocation(locationUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "지역를 삭제하는 API")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteLocation(@RequestBody @Valid LocationDeleteRequest locationDeleteRequest) {
        locationService.deleteLocation(locationDeleteRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
