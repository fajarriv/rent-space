package id.ac.ui.cs.advprog.rentingandbooking.repository;

import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer> {


}
