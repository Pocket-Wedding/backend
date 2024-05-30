package pocket.backend.location.domain;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.backend.location.service.LocationService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationTest {

    @Mock
    private LocationRepository locationRepository;

    private LocationService locationService;

    private Location location;
}