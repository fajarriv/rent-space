package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;

import java.util.List;

public interface SpaceService {
    List<Space> findAll();

    Space findById(Integer id);

    Space create(SpaceRequest request);

    Space updateById(Integer id, SpaceRequest request);

    void deleteById(Integer id);
}
