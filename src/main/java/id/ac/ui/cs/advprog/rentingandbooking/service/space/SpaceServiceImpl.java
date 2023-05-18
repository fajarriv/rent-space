package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.ReservationRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Invoice;
import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import id.ac.ui.cs.advprog.rentingandbooking.repository.SpaceRepository;

import id.ac.ui.cs.advprog.rentingandbooking.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;

    private final SpaceCategoryServiceImpl spaceCategoryService;


    @Override
    public List<Space> findAll() {
        return spaceRepository.findAll();
    }

    @Override
    public List<SpaceResponse> findAllDistinct() {
        return spaceRepository.findAllDistinct();
    }

    @Override
    public List<Space> findAllByType(String typeName) {
        SpaceCategory type = spaceCategoryService.findById(typeName);
        return spaceRepository.findDistinctByCategory(type);
    }

    @Override
    public List<Space> findByName(String name) {
        return spaceRepository.findByName(name);
    }


    @Override
    public Space findById(Integer id) {
        return spaceRepository.findById(id).get();
    }


    @Override
    public Space create(SpaceRequest request) throws ParseException {
        Date date = DateUtils.parseDate(request.getDate());
        spaceCategoryService.findById(request.getCategoryName());
        Space newSpace = Space.builder()
                .name(request.getName())
                .description(request.getDescription())
                .date(date)
                .category(spaceCategoryService.findById(request.getCategoryName()))
                .price(request.getPrice())
                .capacity(request.getCapacity())
                .isAvailable(true)
                .isValidated(false)
                .spaceType(SpaceType.valueOf(request.getType()))
                .build();

        spaceRepository.save(newSpace);
        return newSpace;
    }

    @Override
    public Reservation rent(ReservationRequest request) throws ParseException {
        Date date = DateUtils.parseDate(request.getDate());
        Space space = findById(request.getSpace().getId());
        int totalPrice = space.getPrice() * request.getDuration();
        Invoice newInvoice = Invoice.builder()
                .createdAt((java.sql.Date) date)
                .totalPrice(totalPrice)
                .isCompleted(false)
                .paymentMethod(request.getPaymentMethod())
                .build();

        return null;
    }

    @Override
    public Space updateById(Integer id, SpaceRequest request) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
