package id.ac.ui.cs.advprog.rentingandbooking.repository;


import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceCategoryRepository extends JpaRepository<SpaceCategory, String> {

    SpaceCategory findByName(String name);

    List<SpaceCategory> findByType(SpaceType type);
}