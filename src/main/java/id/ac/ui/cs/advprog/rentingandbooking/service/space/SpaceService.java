package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.ReservationRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;

import java.text.ParseException;
import java.util.List;

public interface SpaceService {
    List<Space> findAll();

    List<SpaceResponse> findAllDistinct();

    List<SpaceResponse> findAllByCategory(String typeName);

    List<Space> findByName(String name);

    Space findById(Integer id);

    List<Space> create(SpaceRequest request) throws ParseException;

    Reservation rent(ReservationRequest request) throws ParseException;

    Space updateById(Integer id, SpaceRequest request);

    void deleteById(Integer id);
}
