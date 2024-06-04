package pocket.backend.hall.service;

import org.springframework.stereotype.Service;
import pocket.backend.hall.domain.Hall;
import pocket.backend.hall.domain.HallRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {

    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public Hall saveHall(Hall hall) {
        return hallRepository.save(hall);
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Optional<Hall> getHallById(Long id) {
        return hallRepository.findById(id);
    }

    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }


}