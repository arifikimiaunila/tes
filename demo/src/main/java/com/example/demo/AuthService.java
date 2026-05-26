@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean verifyPassword(String email, String rawPassword) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return passwordEncoder.matches(rawPassword, user.getPasswordHash());
    }

    public boolean verifyOtp(User user, String otp) {
        // Gunakan library TOTP (misalnya Google Authenticator)
        return TotpUtil.verifyCode(user.getTwoFactorSecret(), otp);
    }
}
