package fr.sdv.spe_dev.services;

import fr.sdv.spe_dev.dtos.InputUserDTO;
import fr.sdv.spe_dev.dtos.OutputUserDTO;
import fr.sdv.spe_dev.entities.User;
import fr.sdv.spe_dev.exceptions.UserNotFoundException;
import fr.sdv.spe_dev.mappers.UserMapper;
import fr.sdv.spe_dev.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public OutputUserDTO getById(Long userId) {
        return userMapper.toUserDTO(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User #%d not found".formatted(userId))));
    }

    public OutputUserDTO getByEmail(String email) {
        return userMapper.toUserDTO(userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User %s not found".formatted(email))));
    }

    public OutputUserDTO register(InputUserDTO userDTO) {
        User user = userMapper.toUser(userDTO);

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userMapper.toUserDTO(userRepository.save(user));
    }

    public OutputUserDTO update(Long userId, InputUserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User #%d not found".formatted(userId)));

        userMapper.updateUserFromDto(userDTO, user);

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        return userMapper.toUserDTO(userRepository.save(user));
    }
}
