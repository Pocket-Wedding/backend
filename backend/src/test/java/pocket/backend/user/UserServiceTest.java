package pocket.backend.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pocket.backend.common.exceptions.AuthException;
import pocket.backend.user.domain.AuthProvider;
import pocket.backend.user.domain.Role;
import pocket.backend.user.domain.User;
import pocket.backend.user.domain.UserRepository;
import pocket.backend.user.dto.SignUpRequest;
import pocket.backend.user.service.UserService;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        user = User.builder()
                .id(1L)
                .email("tees3359@gmail.com")
                .name("test")
                .password("1234")
                .phoneNumber("010-1234-5678")
                .bank("신한")
                .accountNumber("1234567890")
                .provider(null)
                .providerId(null)
                .build();

    }

    @DisplayName("회원가입을 한다.")
    @Test
    void registerUser() {
        SignUpRequest signUpRequest = new SignUpRequest("a@gmail.com", "test", "1234");
        User user = new User();
        user.setId(1L);
        user.setEmail("a@gmail.com");
        user.setName("test");
        user.setPassword("1234");
        user.setRole(Role.ROLE_USER);
        user.setPhoneNumber(null);
        user.setBank(null);
        user.setAccountNumber(null);
        user.setProvider(null);
        user.setProviderId(null);
        user.setDeleted(false);


        when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        assertThat(userService.registerUser(signUpRequest)).isEqualTo(1L);
    }

    @DisplayName("중복되는 이메일일 경우 예외처리한다.")
    @Test
    void registerUserWithException() {
        SignUpRequest signUpRequest = new SignUpRequest("아이디", "a@email.com", "password");

        User user = new User();
        user.setId(1L);
        user.setEmail("a@email.com");
        user.setEmailVerified(true);
        user.setName("hi");
        user.setPassword("password");
        user.setProvider(AuthProvider.local);
        user.setRole(Role.ROLE_USER);
        user.setProviderId("local");

        when(userRepository.existsByEmail(any())).thenReturn(true);

        assertThatThrownBy(() -> userService.registerUser(signUpRequest))
                .isInstanceOf(AuthException.class)
                .hasMessageContaining("존재하는");
    }

    @DisplayName("회원 탈퇴한다.")
    @Test
    void deleteCurrentUser() {
        userService.deleteCurrentUser(user);
        verify(userRepository).deleteById(anyLong());
    }
}
