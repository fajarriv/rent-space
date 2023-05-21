package id.ac.ui.cs.advprog.rentingandbooking.exceptions.space;

public class SpaceIdDoesNotExistException extends RuntimeException {

    public SpaceIdDoesNotExistException(Integer id) {
        super("Space with id " + id + " does not exist");
    }
}
