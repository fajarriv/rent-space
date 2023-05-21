package id.ac.ui.cs.advprog.rentingandbooking.exceptions.space;

import id.ac.ui.cs.advprog.rentingandbooking.model.space.Space;

public class SpaceDoesNotAvailableException extends RuntimeException {

    public SpaceDoesNotAvailableException(Space space) {
        super("Space " + space.getName() + " is not available for " + space.getDate());
    }
}
