package id.ac.ui.cs.advprog.rentingandbooking.repository;


import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceTypeRepository extends JpaRepository<SpaceType, String> {


}