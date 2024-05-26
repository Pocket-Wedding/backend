package pocket.backend.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@NoArgsConstructor(access=lombok.AccessLevel.PUBLIC)
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String bank;

    @Column(nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    private Role role;

    private boolean deleted;

    @Builder
    public User(Long id,String email, String name, String password, String phoneNumber, String bank, String accountNumber, AuthProvider provider, String providerId, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
    }

    public boolean isNotPossibleToAccessUserCocktail(Long userId) {
        return !this.id.equals(userId) && this.role != Role.ROLE_ADMIN;
    }

    public String roleName() {
        return role.name();
    }

}