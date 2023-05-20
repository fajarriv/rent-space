package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.reservation.ReservationRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.reservation.Reservation;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceStatus;
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
    public List<SpaceResponse> findAllByCategory(String typeName) {
        SpaceCategory type = spaceCategoryService.findByName(typeName);
        return spaceRepository.findDistinctByCategory(type);
    }

    @Override
    public List<Space> findByName(String name) {
        return spaceRepository.findByName(name);
    }


    @Override
    public Space findById(Integer id) {
        return spaceRepository.findById(id).orElse(null);
    }


    @Override
    public List<Space> create(SpaceRequest request) throws ParseException {
//        Iterate through the list of date from request.getDate() then create a new space for each date
        List<Date> allDate = request.getDate();
        SpaceCategory category = spaceCategoryService.findByName(request.getCategoryName());
        for (Date date : allDate) {
            Space newSpace = Space.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .date(date)
                    .category(category)
                    .price(request.getPrice())
                    .capacity(request.getCapacity())
                    .isAvailable(true)
                    .status(SpaceStatus.ON_REVIEW)
                    .facilities(request.getFacilities())
                    .build();
            spaceRepository.save(newSpace);

        }
        return spaceRepository.findByName(request.getName()).stream().toList();
    }

    @Override
    public Space updateById(Integer id, SpaceRequest request) {
        return null;
    }

    @Override
    public void updateAvailibility(Integer id, Boolean currentStatus) {
        Space space = this.findById(id);
        space.setIsAvailable(!currentStatus);
        spaceRepository.save(space);
    }

    @Override
    public void deleteById(Integer id) {

    }

}
