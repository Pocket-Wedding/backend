package pocket.backend.hall.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pocket.backend.common.exceptions.DuplicatedException;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.domain.HallRepository;
import pocket.backend.hall.dto.HallRegisterRequestDTO;
import pocket.backend.hall.dto.HallUpdateRequestDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class HallService {

    private final HallRepository hallRepository;

    @Transactional
    public Optional<Hall> registerHall(HallRegisterRequestDTO hallRegisterRequestDTO) {

        if (hallRepository.existsByName(hallRegisterRequestDTO.getName())) {
            throw new DuplicatedException(ErrorCode.DUPLICATED_CATEGORY_NAME);
        }

        Hall hall = Hall.builder()
                .name(hallRegisterRequestDTO.getName())
                .address(hallRegisterRequestDTO.getAddress())
                .phoneNumber(hallRegisterRequestDTO.getPhoneNumber())
                .hallForm(hallRegisterRequestDTO.getHallForm())
                .price(hallRegisterRequestDTO.getPrice())
                .menu(hallRegisterRequestDTO.getMenu())
                .seat(hallRegisterRequestDTO.getSeat())
                .weddingForm(hallRegisterRequestDTO.getWeddingForm())
                .image(hallRegisterRequestDTO.getImage())
                .description(hallRegisterRequestDTO.getDescription())
                .count(hallRegisterRequestDTO.getCount())
                .build();

        return Optional.of(hallRepository.save(hall));
    }

    public Hall getHall(Long id) {
        return hallRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid hall Id:" + id));
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Optional<Void> updateHall(HallUpdateRequestDTO hallUpdateRequestDTO) {
        Hall hall = hallRepository.findByName(hallUpdateRequestDTO.getName()).orElseThrow(() ->
                new IllegalArgumentException("Invalid hall Id:" + hallUpdateRequestDTO.getName()));

        hall.update(hallUpdateRequestDTO);
        return Optional.empty();
    }

    public void deleteHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid hall Id:" + id));
        hallRepository.delete(hall);
    }
}