package num.diplom.medicalsystem.security.auth;

import num.diplom.medicalsystem.security.TokenHelper;
import num.diplom.medicalsystem.service.impl.MSUserDetailsService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
    private final TokenHelper tokenHelper;
    private final MSUserDetailsService userDetailsService;

    @Autowired
    public TokenAuthenticationFilter(
            TokenHelper tokenHelper,
            MSUserDetailsService userDetailsService
    ) {
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain chain
    ) throws ServletException, IOException {
        String username;
        String authToken = tokenHelper.getToken(request);
        LOGGER.info("authToken: {}", authToken);
        if (authToken != null) {
            username = tokenHelper.getUsernameFromToken(authToken);
            LOGGER.info("username: {}", username);
            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (tokenHelper.validateToken(authToken, userDetails)) {
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
