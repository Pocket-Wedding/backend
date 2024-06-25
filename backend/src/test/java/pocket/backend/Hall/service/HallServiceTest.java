package pocket.backend.Hall.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.domain.HallRepository;
import pocket.backend.hall.dto.HallRegisterRequestDTO;
import pocket.backend.hall.service.HallService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HallServiceTest {

    @Mock
    private HallRepository hallRepository;

    @InjectMocks
    private HallService hallService;

    private Hall hall;

    @BeforeEach
    void setUp() {
        hallService = new HallService(hallRepository);
        hall = Hall.builder()
                .name("test")
                .build();
    }

    @DisplayName("웨딩홀을 등록한다.")
    @Test
    void registerHall() {
        HallRegisterRequestDTO hallRegisterRequestDTO = new HallRegisterRequestDTO(
                "test", "asdf", "asdf", "asdf", 10, "asdf  ", 10, "asdf", "asdf", "asdf", 10
        );

        Hall hall = Hall.builder()
                .name("test")
                .address("test")
                .phoneNumber("test")
                .hallForm("test")
                .price(10)
                .menu("test")
                .seat(10)
                .weddingForm("test")
                .image("test")
                .description("test")
                .count(10)
                .build();

        when(hallRepository.existsByName(hallRegisterRequestDTO.getName())).thenReturn(false);
        when(hallRepository.save(any())).thenReturn(hall);

        assertThat(hallService.registerHall(hallRegisterRequestDTO)).isEqualTo(Optional.of(hall));
        verify(hallRepository).save(any());
    }

}

