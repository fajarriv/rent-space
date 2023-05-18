package id.ac.ui.cs.advprog.rentingandbooking.controller;


import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.service.space.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/space")
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping("/all-spaces")
    public ResponseEntity<List<Space>> getAllSpace() {
        List<Space> response;
        response = spaceService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<List<SpaceResponse>> getAllDistinct() {
        List<SpaceResponse> response;
        response = spaceService.findAllDistinct();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Space>> getSpaceByName(@PathVariable String name) {
        List<Space> response;
        response = spaceService.findByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-category/{typeName}")
    public ResponseEntity<List<Space>> getSpaceByType(@PathVariable String typeName) {
        List<Space> response;
        response = spaceService.findAllByType(typeName);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<Space> addSpace(@RequestBody SpaceRequest request) {
        Space response;
        try {
            response = spaceService.create(request);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(response);
    }
}