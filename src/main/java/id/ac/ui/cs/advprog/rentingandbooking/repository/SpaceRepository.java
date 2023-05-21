package id.ac.ui.cs.advprog.rentingandbooking.repository;

import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer> {

    @NonNull
    Optional<Space> findById(@NonNull Integer id);

    @Query(value = "SELECT DISTINCT new id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse(s.name,s.owner,s.description,s.category ,s.capacity,s.price) FROM Space s WHERE s.status = 'VALIDATED'")
    List<SpaceResponse> findAllDistinct();
    @Query(value = "SELECT DISTINCT s FROM Space s WHERE s.status <> 'VALIDATED'")
    List<Space> findDistinctAdmin();
    List<Space> findByName(@NonNull String name);

    @Query(value = "SELECT DISTINCT new id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse(s.name,s.owner,s.description,s.category ,s.capacity,s.price) FROM Space s WHERE s.category = ?1 AND s.status = 'VALIDATED'")
    List<SpaceResponse> findDistinctByCategory(SpaceCategory category);
    @Query(value = "SELECT DISTINCT s FROM Space s WHERE s.owner = ?1")
    List<Space> findMySpaces(@NonNull String owner);
}
