@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        if (!authService.verifyPassword(req.getEmail(), req.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        // Step 1: Password benar → minta OTP
        return ResponseEntity.ok("Password valid, please enter OTP");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest req) {
        User user = authService.getUser(req.getEmail());
        if (!authService.verifyOtp(user, req.getOtp())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
