package id.ac.ui.cs.advprog.rentingandbooking.exceptions.space;

public class SpaceNameDoesNotExistException extends RuntimeException{
    public SpaceNameDoesNotExistException(String name) {
        super("Space with name " + name + " does not exist");
    }
}
