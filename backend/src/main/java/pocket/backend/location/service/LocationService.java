package pocket.backend.location.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pocket.backend.common.exceptions.DuplicatedException;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.common.exceptions.NotFoundException;
import pocket.backend.location.domain.Location;
import pocket.backend.location.domain.LocationRepository;
import pocket.backend.location.dto.LocationDeleteRequest;
import pocket.backend.location.dto.LocationRegisterRequest;
import pocket.backend.location.dto.LocationResponse;
import pocket.backend.location.dto.LocationUpdateRequest;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access= AccessLevel.PUBLIC)
@Service
@Slf4j
public class LocationService{

    private final LocationRepository locationRepository;

    // 해당 위치의 업체가 몇개인지 확인하는 메서드
    public Long countLocation(String name) {
        Location findLocation = locationRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );

        return findLocation.getTotalCount();
    }

    public Long getLocationIdByName(String name)
    {
        log.info("name : {}", name);
        Location findLocation = locationRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );

        return findLocation.getId();
    }

    public Optional<Location> getLocationByName(String name) {
        return locationRepository.findByName(name);
    }

    public List<LocationResponse> getAllLocation() {
        return LocationResponse.toList(locationRepository.findAll());
    }
    // 해당 위치를 등록하는 메서드
    @Transactional
    public void registerLocation(LocationRegisterRequest locationRegisterRequest) {
        if(locationRepository.existsByName(locationRegisterRequest.getName())) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_LOCATION_NAME);
        }
        Location location = Location.builder()
                .name(locationRegisterRequest.getName())
                .build();

        locationRepository.save(location);
    }

    // 위치의 이름을 수정하는 메서드
    @Transactional
    public void updateLocation(LocationUpdateRequest locationUpdateRequest) {
        Location findLocation = locationRepository.findByName(locationUpdateRequest.getPrevName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );
        findLocation.updateLocation(locationUpdateRequest);
    }

    // 위치를 삭제하는 메서드
    @Transactional
    public void deleteLocation(LocationDeleteRequest locationDeleteRequest) {
        Location findLocation = locationRepository.findByName(locationDeleteRequest.getName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );
        locationRepository.delete(findLocation);
    }
}
