package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceCategoryRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
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
    public SpaceCategory findById(String name) {
        return spaceCategoryRepository.findById(name).get();
    }

    @Override
    public SpaceCategory create(SpaceCategoryRequest spaceCategoryRequest) {
        SpaceCategory newSpaceCategory = SpaceCategory.builder()
                .name(spaceCategoryRequest.getName())
                .build();
        spaceCategoryRepository.save(newSpaceCategory);
        return newSpaceCategory;
    }
}
