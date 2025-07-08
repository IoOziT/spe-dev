package fr.sdv.spe_dev.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {
    @NotNull(message = "Email should be present")
    private String email;
    @NotNull(message = "Password should be present")
    private String password;
}
