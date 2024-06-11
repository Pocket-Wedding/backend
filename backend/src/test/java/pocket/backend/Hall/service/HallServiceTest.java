package pocket.backend.Hall.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.domain.HallRepository;
import pocket.backend.hall.service.HallService;

@ExtendWith(MockitoExtension.class)
public class HallServiceTest {

    @Mock
    private HallRepository hallRepository;

    private HallService hallService;

    private Hall hall;

}
