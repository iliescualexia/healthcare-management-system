package org.example.springhealthcaremanagement.services.user;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.dtos.user.UserRequestDto;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.mappers.UserMapper;
import org.example.springhealthcaremanagement.repositories.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    @Override
    public ResponseEntity<UserRequestDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(user -> ResponseEntity.ok(userMapper.toRequestDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public UserDto update(UserRequestDto userRequestDto) {
        User user = findByIdHelper(userRequestDto.getId());
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(encoder.encode(userRequestDto.getPassword()));
        user.setEmail(userRequestDto.getEmail());
        user.setRole(user.getRole());
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    private User findByIdHelper(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
