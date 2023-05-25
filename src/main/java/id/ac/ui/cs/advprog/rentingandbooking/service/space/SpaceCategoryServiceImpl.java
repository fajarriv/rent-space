package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceCategoryRequest;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceCategoryAlreadyExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceCategoryDoesNotExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceTypeDoesNotExistException;
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
        if (isSpaceCategoryDoesNotExist(name)) {
            throw new SpaceCategoryDoesNotExistException(name);
        }
        return spaceCategoryRepository.findByName(name).orElse(null);
    }

    @Override
    public List<SpaceCategory> findByType(String type) {
        if (isSpaceTypeDoesNotExist(type)) {
            throw new SpaceTypeDoesNotExistException(type);
        }
        SpaceType spaceType = SpaceType.valueOf(type.toUpperCase());
        return spaceCategoryRepository.findByType(spaceType);
    }

    @Override
    public SpaceCategory create(SpaceCategoryRequest spaceCategoryRequest) {
        if (!isSpaceCategoryDoesNotExist(spaceCategoryRequest.getName())) {
            throw new SpaceCategoryAlreadyExistException(spaceCategoryRequest.getName());
        }
        SpaceCategory newSpaceCategory = SpaceCategory.builder()
                .name(spaceCategoryRequest.getName())
                .type(SpaceType.valueOf(spaceCategoryRequest.getType()))
                .build();
        spaceCategoryRepository.save(newSpaceCategory);
        return newSpaceCategory;
    }

    private boolean isSpaceCategoryDoesNotExist(String name) {
        return spaceCategoryRepository.findByName(name).isEmpty();
    }

    private boolean isSpaceTypeDoesNotExist(String type) {
//        Check type is valid enum or not
        try {
            SpaceType.valueOf(type.toUpperCase());
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}
