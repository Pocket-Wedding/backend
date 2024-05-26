package pocket.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pocket.backend.user.domain.User;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PUBLIC)
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String bank;
    private String accountNumber;
    private String provider;
    private String providerId;
    private String role;
    private boolean deleted;

    public static UserResponse of(User user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhoneNumber(),
                user.getBank(),
                user.getAccountNumber(),
                user.getProvider().name(),
                user.getProviderId(),
                user.roleName(),
                user.isDeleted()
        );

    }
}
