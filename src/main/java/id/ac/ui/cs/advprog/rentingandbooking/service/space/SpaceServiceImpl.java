package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
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

    private final SpaceTypeServiceImpl spaceTypeService;


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
        SpaceType type = spaceTypeService.findById(typeName);
        return spaceRepository.findDistinctByType(type);
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
        spaceTypeService.findById(request.getTypeName());
        Space newSpace = Space.builder()
                .name(request.getName())
                .description(request.getDescription())
                .date(date)
                .type(spaceTypeService.findById(request.getTypeName()))
                .price(request.getPrice())
                .capacity(request.getCapacity())
                .isAvailable(true)
                .build();

        spaceRepository.save(newSpace);
        return newSpace;
    }

    @Override
    public Space updateById(Integer id, SpaceRequest request) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
