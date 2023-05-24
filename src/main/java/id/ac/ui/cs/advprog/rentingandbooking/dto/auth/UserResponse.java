package id.ac.ui.cs.advprog.rentingandbooking.dto.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private List<String> permissions;
    private boolean active;
}
