package pocket.backend.location.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pocket.backend.common.exceptions.DuplicatedException;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.common.exceptions.NotFoundException;
import pocket.backend.location.domain.Location;
import pocket.backend.location.domain.LocationRepository;

@RequiredArgsConstructor(access= AccessLevel.PUBLIC)
@Service
public class LocationService{
    @Autowired
    private LocationRepository locationRepository;

    // 해당 위치의 업체가 몇개인지 확인하는 메서드
    public Long countLocation(String name) {
        Location findLocation = locationRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_LOCATION)
        );

        return findLocation.getTotalCount();
    }

    // 해당 위치를 등록하는 메서드
    public void registerLocation(String name) {
        if(locationRepository.existsByName(name)) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_LOCATION_NAME);
        }
        Location location = Location.builder()
                .name(name)
                .build();

        locationRepository.save(location);
    }
}
