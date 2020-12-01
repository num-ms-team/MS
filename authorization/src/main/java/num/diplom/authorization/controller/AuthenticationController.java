package num.diplom.authorization.controller;

import num.diplom.authorization.controller.dto.UserTokenState;
import num.diplom.authorization.dto.UserDto;
import num.diplom.authorization.security.TokenHelper;
import num.diplom.authorization.service.impl.MSUserDetailsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.Validate;

import static num.diplom.base.constant.RestConstants.INVALID_INPUT;

@RestController
@RequestMapping("aim")
public class AuthenticationController {
    private final TokenHelper tokenHelper;
    private final MSUserDetailsService userDetailsService;

    @Lazy
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(
            TokenHelper tokenHelper,
            AuthenticationManager authenticationManager,
            MSUserDetailsService userDetailsService
    ) {
        this.tokenHelper = tokenHelper;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody UserDto body) {
        Validate.notNull(body, INVALID_INPUT);
        // Perform the security
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            body.getUsername(),
                            body.getPassword()
                    )
            );

            // Inject into security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // token creation
            User user = (User) authentication.getPrincipal();
            String jws = tokenHelper.generateToken(user.getUsername());
            int expiresIn = tokenHelper.getExpiredIn();
            return ResponseEntity.ok(new UserTokenState(jws, expiresIn));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
