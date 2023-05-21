package id.ac.ui.cs.advprog.rentingandbooking.controller.space;


import id.ac.ui.cs.advprog.rentingandbooking.dto.Temporary;
import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse;
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
    @GetMapping("/me")
    public ResponseEntity<List<Space>> getMySpace(@RequestBody Temporary request) {
//        Get current user
        List<Space> response;
        response = spaceService.findMySpaces(request.getCurrentUser());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/admin")
    public ResponseEntity<List<Space>> getAllAdminDistinct() {
        List<Space> response;
        response = spaceService.findSpacesForAdmin();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<Space>> getSpaceByName(@PathVariable String name) {
        List<Space> response;
        response = spaceService.findByName(name);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<SpaceResponse>> getSpaceByType(@PathVariable String categoryName) {
        List<SpaceResponse> response;
        response = spaceService.findAllByCategory(categoryName);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<List<Space>> createSpaces(@RequestBody SpaceRequest request) {
        List<Space> response;
        response = spaceService.create(request);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{name}/{status}")
    public ResponseEntity<String> updateSpaceStatus(@PathVariable String name, @PathVariable String status) {
        String response;
        response = spaceService.updateStatusByName(name, status);
        return ResponseEntity.ok(response);
    }
}