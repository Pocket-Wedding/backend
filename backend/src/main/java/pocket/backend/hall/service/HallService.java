package pocket.backend.hall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.domain.HallRepository;
import pocket.backend.hall.dto.HallRequest;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HallService {

    private final HallRepository hallRepository;

    public Hall createHall(HallRequest hallRequest) {
        Hall hall = Hall.builder()
                .name(hallRequest.getName())
                .address(hallRequest.getAddress())
                .hallForm(hallRequest.getHallForm())
                .price(hallRequest.getPrice())
                .menu(hallRequest.getMenu())
                .seat(hallRequest.getSeat())
                .weddingForm(hallRequest.getWeddingForm())
                .image(hallRequest.getImage())
                .description(hallRequest.getDescription())
                .count(hallRequest.getCount())
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

    public Hall updateHall(Long id, HallRequest hallRequest) {
        Hall hall = hallRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid hall Id:" + id));
        hall.setName(hallRequest.getName());
        hall.setAddress(hallRequest.getAddress());
        hall.setHallForm(hallRequest.getHallForm());
        hall.setPrice(hallRequest.getPrice());
        hall.setMenu(hallRequest.getMenu());
        hall.setSeat(hallRequest.getSeat());
        hall.setWeddingForm(hallRequest.getWeddingForm());
        hall.setImage(hallRequest.getImage());
        hall.setDescription(hallRequest.getDescription());
        hall.setCount(hallRequest.getCount());
        return hallRepository.save(hall);
    }

    public void deleteHall(Long id) {
        Hall hall = hallRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid hall Id:" + id));
        hallRepository.delete(hall);
    }
}