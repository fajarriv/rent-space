package id.ac.ui.cs.advprog.rentingandbooking.service.space;

import id.ac.ui.cs.advprog.rentingandbooking.dto.space.SpaceCategoryRequest;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceCategoryAlreadyExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceCategoryDoesNotExistException;
import id.ac.ui.cs.advprog.rentingandbooking.exceptions.space.SpaceTypeDoesNotExistException;
import id.ac.ui.cs.advprog.rentingandbooking.model.space.SpaceCategory;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpaceCategoryServiceImplTest {

    @InjectMocks
    SpaceCategoryServiceImpl spaceCategoryService;

    @Mock
    SpaceCategoryRepository spaceCategoryRepository;

    SpaceCategory spaceCategory1;
    SpaceCategory spaceCategory2;
    SpaceCategory spaceCategory3;
    List<SpaceCategory> allCategory;

    SpaceCategoryRequest spaceCategoryRequest;


    @BeforeEach
    void setUp() {
        spaceCategory1 = SpaceCategory.builder()
                .name("Villa")
                .type(SpaceType.CLOSED_SPACE)
                .build();
        spaceCategory2 = SpaceCategory.builder()
                .name("Lapangan")
                .type(SpaceType.OPEN_SPACE)
                .build();

        spaceCategory3 = SpaceCategory.builder()
                .name("Aula")
                .type(SpaceType.CLOSED_SPACE)
                .build();

        allCategory = List.of(spaceCategory1, spaceCategory2, spaceCategory3);

        spaceCategoryRequest = SpaceCategoryRequest.builder()
                .name("New Category")
                .type("CLOSED_SPACE")
                .build();
    }

    @Test
    void whenFindAllShouldReturnAllSpaceCategory() {
        when(spaceCategoryRepository.findAll()).thenReturn(allCategory);

        List<SpaceCategory> result = spaceCategoryService.findAll();
        verify(spaceCategoryRepository, atLeastOnce()).findAll();
        assertEquals(allCategory, result);
    }

    @Test
    void whenFindByNameAndFoundShouldReturnSpaceCategory() {
        when(spaceCategoryRepository.findByName(any(String.class))).thenReturn(Optional.of(spaceCategory1));

        SpaceCategory result = spaceCategoryService.findByName("Villa");
        verify(spaceCategoryRepository, atLeastOnce()).findByName(any(String.class));
        assertEquals(spaceCategory1, result);
    }

    @Test
    void whenFindByNameAndNotFoundShoulThrowExceptions() {
        when(spaceCategoryRepository.findByName(any(String.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(SpaceCategoryDoesNotExistException.class, () -> {
            spaceCategoryService.findByName("Villa");
        });
    }

    @Test
    void whenFindByTypeAndFoundShouldReturnListOfCategory() {
        when(spaceCategoryRepository.findByType(any(SpaceType.class))).thenReturn(List.of(spaceCategory1, spaceCategory3));

        List<SpaceCategory> result = spaceCategoryService.findByType("closed_space");
        verify(spaceCategoryRepository, atLeastOnce()).findByType(any(SpaceType.class));
        assertEquals(List.of(spaceCategory1, spaceCategory3), result);
    }

    @Test
    void whenFindByTypeAndTypeNotFoundShouldThrowException() {
        Assertions.assertThrows(SpaceTypeDoesNotExistException.class, () -> {
            spaceCategoryService.findByType("notType");
        });
    }

    @Test
    void whenCreateSpaceCategoryShouldReturnNewSpaceCategory() {
        SpaceCategory newCategory = SpaceCategory.builder()
                .name("New Category")
                .type(SpaceType.CLOSED_SPACE)
                .build();
        when(spaceCategoryRepository.save(any(SpaceCategory.class))).thenAnswer(invocation -> {
            return invocation.getArgument(0, SpaceCategory.class);

        });
        SpaceCategory result = spaceCategoryService.create(spaceCategoryRequest);
        verify(spaceCategoryRepository, atLeastOnce()).save(any(SpaceCategory.class));
        assertEquals(newCategory, result);
    }

    @Test
    void whenCreateSpaceCategoryWithExistingNameShouldThrowException() {
        when(spaceCategoryRepository.findByName(any(String.class))).thenReturn(Optional.of(spaceCategory1));
        Assertions.assertThrows(SpaceCategoryAlreadyExistException.class, () -> {
            spaceCategoryService.create(spaceCategoryRequest);
        });
    }
}
