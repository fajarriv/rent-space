package id.ac.ui.cs.advprog.rentingandbooking.exceptions.space;

public class SpaceCategoryAlreadyExistException extends RuntimeException{

    public SpaceCategoryAlreadyExistException(String name) {
        super("Space Category with name " + name + " already exist");
    }
}
