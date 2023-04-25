package id.ac.ui.cs.advprog.rentingandbooking.repository;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer> {

    @Query(value = "SELECT DISTINCT new id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceResponse(s.name,s.description,s.type,s.capacity,s.price) FROM Space s")
    List<SpaceResponse> findAllDistinct();

    List<Space> findByName(String name);

    List<Space> findDistinctByType(SpaceType type);
}
