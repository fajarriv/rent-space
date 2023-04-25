package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceTypeRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import id.ac.ui.cs.advprog.rentingandbooking.repository.SpaceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceTypeServiceImpl implements SpaceTypeService {

    private final SpaceTypeRepository spaceTypeRepository;

    @Override
    public List<SpaceType> findAll() {
        return spaceTypeRepository.findAll();
    }

    @Override
    public SpaceType findById(String name) {
        return spaceTypeRepository.findById(name).get();
    }

    @Override
    public SpaceType create(SpaceTypeRequest spaceTypeRequest) {
        SpaceType newSpaceType = SpaceType.builder()
                .name(spaceTypeRequest.getName())
                .build();
        spaceTypeRepository.save(newSpaceType);
        return newSpaceType;
    }
}
