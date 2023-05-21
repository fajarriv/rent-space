package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;

import java.util.List;

public interface SpaceService {
    List<Space> findAll();

    List<SpaceResponse> findAllDistinct();

    List<Space> findSpacesForAdmin();

    List<Space>findMySpaces(String ownerName);

    List<SpaceResponse> findAllByCategory(String typeName);

    List<Space> findByName(String name);

    Space findById(Integer id);

    List<Space> create(SpaceRequest request);

    Space updateById(Integer id, SpaceRequest request);

    String updateStatusByName(String name, String status);

    void updateAvailibility(Integer spaceId, Boolean isAvailable);

    void deleteById(Integer id);
}
