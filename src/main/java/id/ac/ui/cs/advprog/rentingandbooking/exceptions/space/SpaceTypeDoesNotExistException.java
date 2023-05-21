package id.ac.ui.cs.advprog.rentingandbooking.exceptions.space;

public class SpaceTypeDoesNotExistException extends RuntimeException{

        public SpaceTypeDoesNotExistException(String name) {
            super("Space Type " + name + " does not exist");
        }
}
