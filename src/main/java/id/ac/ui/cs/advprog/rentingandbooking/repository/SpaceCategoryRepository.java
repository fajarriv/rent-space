package id.ac.ui.cs.advprog.rentingandbooking.repository;


import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceCategoryRepository extends JpaRepository<SpaceCategory, String> {


}