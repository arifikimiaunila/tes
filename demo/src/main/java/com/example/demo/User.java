@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String passwordHash;

    private String twoFactorSecret;

    private boolean enabled = true;

    // getter & setter
}
