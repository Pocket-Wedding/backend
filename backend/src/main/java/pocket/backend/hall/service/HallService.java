package pocket.backend.hall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.domain.HallRepository;
import pocket.backend.hall.dto.HallRegisterRequestDTO;
import pocket.backend.hall.dto.HallUpdateRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HallService {

    private final HallRepository hallRepository;

    public Hall createHall(HallRegisterRequestDTO hallRegister) {
        Hall hall = Hall.builder()
                .name(hallRegister.getName())
                .address(hallRegister.getAddress())
                .phoneNumber(hallRegister.getPhoneNumber())
                .hallForm(hallRegister.getHallForm())
                .price(hallRegister.getPrice())
                .menu(hallRegister.getMenu())
                .seat(hallRegister.getSeat())
                .weddingForm(hallRegister.getWeddingForm())
                .image(hallRegister.getImage())
                .description(hallRegister.getDescription())
                .count(hallRegister.getCount())
                .build();
        return hallRepository.save(hall);
    }

    public Hall getHall(Long id) {
        return hallRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid hall Id:" + id));
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Hall updateHall(Long id, HallUpdateRequestDTO hallUpdate) {
        Hall hall = hallRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid hall Id:" + id));
        hall.setName(hallUpdate.getName());
        hall.setAddress(hallUpdate.getAddress());
        hall.setPhoneNumber(hallUpdate.getPhoneNumber());
        hall.setHallForm(hallUpdate.getHallForm());
        hall.setPrice(hallUpdate.getPrice());
        hall.setMenu(hallUpdate.getMenu());
        hall.setSeat(hallUpdate.getSeat());
        hall.setWeddingForm(hallUpdate.getWeddingForm());
        hall.setImage(hallUpdate.getImage());
        hall.setDescription(hallUpdate.getDescription());
        hall.setCount(hallUpdate.getCount());
        return hallRepository.save(hall);
    }

    public void deleteHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid hall Id:" + id));
        hallRepository.delete(hall);
    }
}