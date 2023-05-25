package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.Util;
import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceRequest;
import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceResponse;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceCategoryDoesNotExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceIdDoesNotExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceNameAlreadyExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceNameDoesNotExistException;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceStatus;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceType;
import id.ac.ui.cs.advprog.rentingandbooking.repository.SpaceCategoryRepository;
import id.ac.ui.cs.advprog.rentingandbooking.repository.SpaceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
class SpaceServiceImplTest {

    @InjectMocks
    private SpaceServiceImpl spaceService;

    @Mock
    private SpaceRepository spaceRepository;

    @Mock
    private SpaceCategoryServiceImpl spaceCategoryService;

    Space space;

    SpaceCategory spaceCategory;

    SpaceResponse spaceResponse;

    SpaceRequest spaceRequest;

    List<String> facilites;

    @BeforeEach
    void setUp() {
        facilites = Arrays.asList("Wifi", "AC", "TV", "Kamar Mandi", "Dapur");

        spaceCategory = SpaceCategory.builder()
                .name("Villa")
                .type(SpaceType.CLOSED_SPACE)
                .build();

        space = Space.builder()
                .id(1)
                .name("Villa ubud")
                .owner("Rayhan")
                .description("Villa dengan pemandangan indah dekat pantai")
                .date(Util.parseDate("2023-03-03"))
                .category(spaceCategory)
                .price(1000000)
                .capacity(25)
                .isAvailable(true)
                .status(SpaceStatus.ON_REVIEW)
                .facilities(facilites)
                .build();

        spaceResponse = SpaceResponse.builder()
                .name("Villa ubud")
                .owner("Rayhan")
                .price(1000000)
                .capacity(25)
                .description("Villa dengan pemandangan indah dekat pantai")
                .category(spaceCategory)
                .build();

        spaceRequest = SpaceRequest.builder()
                .name("Villa ubud")
                .owner("Rayhan")
                .description("Villa dengan pemandangan indah dekat pantai")
                .date(Collections.singletonList(Util.parseDate("2023-03-03")))
                .categoryName("Villa")
                .price(1000000)
                .capacity(25)
                .facilities(facilites)
                .build();
    }

    @Test
    void whenFindAllReturnListOfSpace() {
        List<Space> allSpaces = List.of(space);
        when(spaceRepository.findAll()).thenReturn(allSpaces);

        List<Space> result = spaceService.findAll();
        verify(spaceRepository, atLeastOnce()).findAll();
        assertEquals(allSpaces, result);
    }

    @Test
    void whenFindAllDistinctShouldReturnListOfSpaceResponse() {
        List<SpaceResponse> distinctSpaces = List.of(spaceResponse);
        when(spaceRepository.findAllDistinct()).thenReturn(distinctSpaces);

        List<SpaceResponse> result = spaceService.findAllDistinct();
        verify(spaceRepository, atLeastOnce()).findAllDistinct();
        assertEquals(distinctSpaces, result);
    }

    @Test
    void whenFindSpacesForAdminShouldReturnListOfSpaceResponse() {
        List<SpaceResponse> spacesForAdmin = List.of(spaceResponse);
        when(spaceRepository.findDistinctAdmin()).thenReturn(spacesForAdmin);

        List<SpaceResponse> result = spaceService.findSpacesForAdmin();
        verify(spaceRepository, atLeastOnce()).findDistinctAdmin();
        assertEquals(spacesForAdmin, result);
    }

    @Test
    void whenFindMySpacesShouldReturnListOfSpace() {
        List<Space> mySpaces = List.of(space);
        when(spaceRepository.findMySpaces("Rayhan")).thenReturn(mySpaces);

        List<Space> result = spaceService.findMySpaces("Rayhan");
        verify(spaceRepository, atLeastOnce()).findMySpaces("Rayhan");
        assertEquals(mySpaces, result);
    }

    @Test
    void whenFindAllByCategoryShouldReturnListOfSpaceResponse() {
        String typeName = "Villa";
        SpaceCategory spaceCategory = SpaceCategory.builder()
                .name(typeName)
                .type(SpaceType.CLOSED_SPACE)
                .spaces(new ArrayList<>())
                .build();
        List<SpaceResponse> expectedResponse = List.of(new SpaceResponse());
        when(spaceCategoryService.findByName(typeName)).thenReturn(spaceCategory);
        when(spaceRepository.findDistinctByCategory(spaceCategory)).thenReturn(expectedResponse);

        List<SpaceResponse> result = spaceService.findAllByCategory(typeName);
        verify(spaceCategoryService).findByName(typeName);
        verify(spaceRepository).findDistinctByCategory(spaceCategory);

        assertEquals(expectedResponse, result);
    }

    @Test
    void whenFindByNameAndFoundShouldReturnSpaceResponse() {
        String name = "Villa";
        when(spaceRepository.findByName(name)).thenReturn(List.of(space));

        List<Space> result = spaceService.findByName(name);
        verify(spaceRepository, atLeastOnce()).findByName(name);
        assertEquals(List.of(space), result);
    }

    @Test
    void whenFindByNameAndNotFoundShouldThrowException() {
        String name = "Villa";
        when(spaceRepository.findByName(name)).thenReturn(List.of());

        Assertions.assertThrows(SpaceNameDoesNotExistException.class, () -> {
            spaceService.findByName(name);
        });
    }

    @Test
    void whenFindByIdAndFoundShouldReturnSpace() {
        int id = 1;
        when(spaceRepository.findById(any(Integer.class))).thenReturn(Optional.of(space));

        Space result = spaceService.findById(id);
        verify(spaceRepository, atLeastOnce()).findById(any(Integer.class));
        assertEquals(space, result);
    }

    @Test
    void whenFindByIdAndNotFoundShouldThrowException() {
        int id = 1;
        when(spaceRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(SpaceIdDoesNotExistException.class, () -> {
            spaceService.findById(id);
        });
    }

    @Test
    void whenCreateSpaceShouldReturnListOfCreatedSpace() {
        when(spaceCategoryService.findByName(any(String.class))).thenReturn(spaceCategory);
        when(spaceRepository.findByName(any(String.class))).thenReturn(List.of()).thenReturn(List.of(space));
        when(spaceRepository.save(any(Space.class))).thenAnswer(invocation -> {
            var space = invocation.getArgument(0, Space.class);
            space.setId(1);
            return space;
        });
        List<Space> result = spaceService.create(spaceRequest);
        verify(spaceRepository, atLeastOnce()).save(space);
        assertEquals(List.of(space), result);
    }

    @Test
    void whenCreateSpaceAndCategoryNotFoundShouldThrowException() {
        when(spaceCategoryService.findByName(any(String.class))).thenThrow(SpaceCategoryDoesNotExistException.class);

        Assertions.assertThrows(SpaceCategoryDoesNotExistException.class, () -> {
            spaceService.create(spaceRequest);
        });
    }

    @Test
    void whenCreateSpaceAndNameAlreadyExistShouldThrowException() {

        when(spaceRepository.findByName(any(String.class))).thenReturn(List.of(space));

        Assertions.assertThrows(SpaceNameAlreadyExistException.class, () -> {
            spaceService.create(spaceRequest);
        });
    }

    @Test
    void whenUpdateStatusSpaceShouldReturnMessage() {
        when(spaceRepository.findByName(any(String.class))).thenReturn(List.of(space));
        when(spaceRepository.save(any(Space.class))).thenAnswer(invocation -> {
            var space = invocation.getArgument(0, Space.class);
            space.setStatus(SpaceStatus.VALIDATED);
            return space;
        });
        String status = "validated";
        String result = spaceService.updateStatusByName(space.getName(), status);
        assertEquals(SpaceStatus.VALIDATED, space.getStatus());
        assertEquals("Space with name " + space.getName() + " has been updated to " + status, result);
    }

    @Test
    void whenUpdateAvailabilitySpaceShouldChangeIsAvailableFlag() {

        when(spaceRepository.findById(any(Integer.class))).thenReturn(Optional.of(space));

        // first switch flag
        Boolean flagBefore = space.getIsAvailable();
        spaceService.updateAvailibility(space.getId(), space.getIsAvailable());
        assertEquals(!flagBefore, space.getIsAvailable());
        // second switch flag
        flagBefore = space.getIsAvailable();
        spaceService.updateAvailibility(space.getId(), space.getIsAvailable());
        assertEquals(!flagBefore, space.getIsAvailable());

    }

    @Test
    void whenDeleteSpaceShouldCallDeleteOnRepository() {
        int id = 1;
        when(spaceRepository.findById(any(Integer.class))).thenReturn(Optional.of(space));
        spaceService.deleteById(id);
        verify(spaceRepository, atLeastOnce()).delete(space);
    }
}

