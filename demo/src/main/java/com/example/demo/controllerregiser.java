import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password) {
        try {
            userService.registerUser(username, email, password);
            return "Registrasi berhasil!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
