package id.ac.ui.cs.advprog.rentingandbooking.exceptions.space;

public class SpaceCategoryDoesNotExistException extends RuntimeException{

    public SpaceCategoryDoesNotExistException(String name) {
        super("Space Category with name " + name + " does not exist");
    }
}
