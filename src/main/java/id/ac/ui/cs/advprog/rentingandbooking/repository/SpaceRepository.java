package id.ac.ui.cs.advprog.rentingandbooking.repository;

import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer> {

    @Query(value = "SELECT DISTINCT new id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse(s.name,s.description,s.category ,s.capacity,s.price) FROM Space s")
    List<SpaceResponse> findAllDistinct();

    List<Space> findByName(String name);

    @Query(value = "SELECT DISTINCT new id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse(s.name,s.description,s.category ,s.capacity,s.price) FROM Space s WHERE s.category = ?1")
    List<SpaceResponse> findDistinctByCategory(SpaceCategory category);

}
