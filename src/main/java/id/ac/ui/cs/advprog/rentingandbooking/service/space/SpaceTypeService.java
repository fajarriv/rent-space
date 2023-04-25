package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceTypeRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;

import java.util.List;

public interface SpaceTypeService {
    List<SpaceType> findAll();

    SpaceType findById(String name);

    SpaceType create(SpaceTypeRequest spaceTypeRequest);
}
