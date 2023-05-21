package id.ac.ui.cs.advprog.rentingandbooking.exceptions.space;

public class SpaceNameAlreadyExistException extends RuntimeException{

        public SpaceNameAlreadyExistException(String name) {
            super("Space with name " + name + " already exist");
        }
}
