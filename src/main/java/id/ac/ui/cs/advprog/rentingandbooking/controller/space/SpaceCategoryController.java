package id.ac.ui.cs.advprog.rentingandbooking.controller.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceCategoryRequest;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import id.ac.ui.cs.advprog.rentingandbooking.service.space.SpaceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/space/category")
@RequiredArgsConstructor
public class SpaceCategoryController {

    private final SpaceCategoryService spaceCategoryService;

    @GetMapping("")
    public ResponseEntity<List<SpaceCategory>> getAllSpaceCategory() {
        List<SpaceCategory> response;
        response = spaceCategoryService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<SpaceCategory>> getCategoryByType(@PathVariable String type) {
        List<SpaceCategory> response;
        response = spaceCategoryService.findByType(type);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<SpaceCategory> addSpaceCategory(@RequestBody SpaceCategoryRequest request) {
        SpaceCategory response;
        response = spaceCategoryService.create(request);
        return ResponseEntity.ok(response);
    }

}
