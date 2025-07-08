package fr.sdv.spe_dev.controllers;

import fr.sdv.spe_dev.dtos.InputUserDTO;
import fr.sdv.spe_dev.dtos.OutputUserDTO;
import fr.sdv.spe_dev.services.UserService;
import fr.sdv.spe_dev.validation.groups.OnUpdate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public OutputUserDTO getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("/{id}")
    public OutputUserDTO updateUser(@PathVariable("id") Long userId,
                        @RequestBody @Validated(OnUpdate.class) InputUserDTO userDTO) {
        return userService.update(userId, userDTO);
    }
}
