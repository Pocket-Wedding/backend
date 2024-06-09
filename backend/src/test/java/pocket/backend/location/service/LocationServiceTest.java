package pocket.backend.location.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.backend.location.domain.Location;
import pocket.backend.location.domain.LocationRepository;
import pocket.backend.location.dto.LocationDeleteRequest;
import pocket.backend.location.dto.LocationRegisterRequest;
import pocket.backend.location.dto.LocationUpdateRequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class LocationServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationService locationService;

    Location location;

    LocationRegisterRequest locationRegisterRequest;

    LocationUpdateRequest locationUpdateRequest;

    LocationDeleteRequest locationDeleteRequest;

    @BeforeEach
    void setUp(){
        location = Location.builder()
                .name("test")
                .build();

        locationRegisterRequest = new LocationRegisterRequest("test");

        locationUpdateRequest = new LocationUpdateRequest("test","테스트하나");

        locationDeleteRequest = new LocationDeleteRequest("테스트 둘");
    }

    @DisplayName("지역을 등록한다.")
    @Test
    void registerLocation(){
        //given
        //when
        when(locationRepository.save(any())).thenReturn(location);
        //then
        locationService.registerLocation(locationRegisterRequest);
        assertThat(locationRepository.count()).isEqualTo(2);
        verify(locationRepository).save(any());
    }
}
