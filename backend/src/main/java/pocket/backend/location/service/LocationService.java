package pocket.backend.location.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pocket.backend.common.exceptions.DuplicatedException;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.common.exceptions.NotFoundException;
import pocket.backend.location.domain.Location;
import pocket.backend.location.domain.LocationRepository;
import pocket.backend.location.dto.LocationRequest;

import java.util.Optional;

@RequiredArgsConstructor(access= AccessLevel.PUBLIC)
@Service
public class LocationService{

    private final LocationRepository locationRepository;

    // 해당 위치의 업체가 몇개인지 확인하는 메서드
    public Long countLocation(String name) {
        Location findLocation = locationRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );

        return findLocation.getTotalCount();
    }

    public Long getLocationIdByName(String name) {
        Location findLocation = locationRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );

        return findLocation.getId();
    }

    public Optional<Location> getLocationByName(String name) {
        return locationRepository.findByName(name);
    }
    // 해당 위치를 등록하는 메서드
    @Transactional
    public void registerLocation(LocationRequest locationRequest) {
        if(locationRepository.existsByName(locationRequest.getName())) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_LOCATION_NAME);
        }
        Location location = Location.builder()
                .name(locationRequest.getName())
                .build();

        locationRepository.save(location);
    }

    // 위치의 이름을 수정하는 메서드
    @Transactional
    public void updateLocation(LocationRequest locationRequest) {
        Location findLocation = locationRepository.findByName(locationRequest.getName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );
        findLocation.updateLocation(locationRequest);
    }

    // 위치를 삭제하는 메서드
    @Transactional
    public void deleteLocation(LocationRequest locationRequest) {
        Location findLocation = locationRepository.findByName(locationRequest.getName()).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );
        locationRepository.delete(findLocation);
    }
}
