package id.ac.ui.cs.advprog.rentingandbooking.repository;


import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceCategoryRepository extends JpaRepository<SpaceCategory, String> {

    @NonNull
    Optional<SpaceCategory> findByName(@NonNull String name);

    List<SpaceCategory> findByType(SpaceType type);
}