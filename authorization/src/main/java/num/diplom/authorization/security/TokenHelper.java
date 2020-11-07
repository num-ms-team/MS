package num.diplom.authorization.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import num.diplom.authorization.security.common.TimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenHelper {
    Logger LOGGER = LoggerFactory.getLogger(TokenHelper.class);

    private final String APP_NAME = "medical-system";
    public final String SECRET = "propertyofundefined";
    private final int EXPIRES_IN = 300;
    private final String AUTH_HEADER = "Authorization";

    private final TimeProvider timeProvider;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public TokenHelper(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }


    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        Date a = timeProvider.now();
        try {
            final Claims claims = getAllClaimsFromToken(token);
            claims.setIssuedAt(a);
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, SECRET)
                    .compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setAudience("unknown")
                .setIssuedAt(timeProvider.now())
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        long expiresIn = EXPIRES_IN;
        return new Date(timeProvider.now().getTime() + expiresIn * 1000);
    }

    public int getExpiredIn() {
        return EXPIRES_IN;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        return (username != null && username.equals(user.getUsername()) && !isExpired(created));
    }

    public Boolean validateToken(String token) {
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        return (username != null && !isExpired(created));
    }

    private boolean isExpired(Date created) {
        return timeProvider.now().getTime() - created.getTime() > EXPIRES_IN * 1000;
    }

    public String getToken(HttpServletRequest request) {
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        String authHeader = getAuthHeaderFromHeader(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

}
