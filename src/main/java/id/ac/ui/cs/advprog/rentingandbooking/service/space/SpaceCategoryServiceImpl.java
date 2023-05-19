package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceCategoryRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import id.ac.ui.cs.advprog.rentingandbooking.repository.SpaceCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceCategoryServiceImpl implements SpaceCategoryService {

    private final SpaceCategoryRepository spaceCategoryRepository;

    @Override
    public List<SpaceCategory> findAll() {
        return spaceCategoryRepository.findAll();
    }

    @Override
    public SpaceCategory findByName(String name) {
        return spaceCategoryRepository.findById(name).get();
    }

    @Override
    public List<SpaceCategory> findByType(String type) {
        SpaceType spaceType = SpaceType.valueOf(type.toUpperCase());
        return spaceCategoryRepository.findByType(spaceType);
    }

    @Override
    public SpaceCategory create(SpaceCategoryRequest spaceCategoryRequest) {
        SpaceCategory newSpaceCategory = SpaceCategory.builder()
                .name(spaceCategoryRequest.getName())
                .type(SpaceType.valueOf(spaceCategoryRequest.getType()))
                .build();
        spaceCategoryRepository.save(newSpaceCategory);
        return newSpaceCategory;
    }
}
