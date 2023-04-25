package id.ac.ui.cs.advprog.rentingandbooking.controller;

import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceTypeRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import id.ac.ui.cs.advprog.rentingandbooking.service.space.SpaceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/space/type")
@RequiredArgsConstructor
public class SpaceTypeController {

    private final SpaceTypeService spaceTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<SpaceType>> getAllSpace() {
        List<SpaceType> response;
        // TODO: Lengkapi kode berikut
        response = spaceTypeService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<SpaceType> addSpace(@RequestBody SpaceTypeRequest request) {
        SpaceType response;
        response = spaceTypeService.create(request);
        return ResponseEntity.ok(response);
    }

}
