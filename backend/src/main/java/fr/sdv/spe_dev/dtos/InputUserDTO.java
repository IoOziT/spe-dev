package fr.sdv.spe_dev.dtos;

import fr.sdv.spe_dev.entities.UserRole;
import fr.sdv.spe_dev.validation.groups.OnCreate;
import fr.sdv.spe_dev.validation.groups.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InputUserDTO {
    @Null(message = "User ID should not be specified", groups = {OnCreate.class, OnUpdate.class})
    private Long id;
    @NotNull(message = "Firstname should be present", groups = OnCreate.class)
    private String firstName;
    @NotNull(message = "Lastname should be present", groups = OnCreate.class)
    private String lastName;
    @NotNull(message = "Email should be present", groups = OnCreate.class)
    @Email
    private String email;
    @NotNull(message = "Password should be present", groups = OnCreate.class)
    private String password;
    @NotNull(message = "Role should be present", groups = OnCreate.class)
    @Null(message = "Role can't be changed", groups = OnUpdate.class)
    private UserRole role;
}
