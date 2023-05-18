package id.ac.ui.cs.advprog.rentingandbooking.controller;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceTypeRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import id.ac.ui.cs.advprog.rentingandbooking.service.space.SpaceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/space/type")
@RequiredArgsConstructor
public class SpaceTypeController {

    private final SpaceTypeService spaceTypeService;

    @GetMapping("")
    public ResponseEntity<List<SpaceType>> getAllSpaceType() {
        List<SpaceType> response;
        response = spaceTypeService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<SpaceType> addSpace(@RequestBody SpaceTypeRequest request) {
        SpaceType response;
        response = spaceTypeService.create(request);
        return ResponseEntity.ok(response);
    }

}
