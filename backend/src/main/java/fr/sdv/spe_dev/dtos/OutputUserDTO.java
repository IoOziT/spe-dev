package fr.sdv.spe_dev.dtos;

import fr.sdv.spe_dev.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OutputUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
}
