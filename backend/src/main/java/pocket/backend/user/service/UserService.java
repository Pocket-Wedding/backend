package pocket.backend.user.service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pocket.backend.common.exceptions.AuthException;
import pocket.backend.common.exceptions.ErrorCode;
import pocket.backend.user.domain.User;
import pocket.backend.user.domain.Role;
import pocket.backend.user.domain.UserRepository;
import pocket.backend.user.dto.SignUpRequest;
import pocket.backend.user.dto.UserUpdateRequest;

@RequiredArgsConstructor(access= AccessLevel.PUBLIC)
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public Long registerUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AuthException(ErrorCode.DUPLICATED_EMAIL);
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .name(signUpRequest.getName())
                .password(signUpRequest.getPassword())
                .role(Role.ROLE_USER)
                .build();

        User result = userRepository.save(user);
        return result.getId();
    }

    @Transactional
    public void deleteCurrentUser(User user) {
        userRepository.deletedById(user.getId());
    }

    @Transactional
    public void updateCurrentUser(User user, UserUpdateRequest userUpdateRequest) {
        user.setName(userUpdateRequest.getName());
        userRepository.save(user);
    }
}
