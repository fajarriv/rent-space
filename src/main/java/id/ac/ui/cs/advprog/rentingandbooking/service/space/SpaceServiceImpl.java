package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;


    @Override
    public List<Space> findAll() {
        return spaceRepository.findAll();
    }

    @Override
    public Space findById(Integer id) {
        return null;
    }

    @Override
    public Space create(SpaceRequest request) {
        return null;
    }

    @Override
    public Space updateById(Integer id, SpaceRequest request) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
