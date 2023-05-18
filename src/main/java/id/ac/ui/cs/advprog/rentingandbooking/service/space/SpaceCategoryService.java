package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceCategoryRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;

import java.util.List;

public interface SpaceCategoryService {
    List<SpaceCategory> findAll();

    SpaceCategory findById(String name);

    SpaceCategory create(SpaceCategoryRequest spaceCategoryRequest);
}
