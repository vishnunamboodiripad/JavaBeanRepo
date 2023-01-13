package learn.monsterBash.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import learn.monsterBash.models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class JwtConverter {

    private Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String getTokenFromUser (UserDetails details) {
        AppUser convertedUser = (AppUser) details;

        return Jwts.builder()
                .setIssuer("MonsterBash")
                .setSubject(details.getUsername())
                .claim( "roles", details.getAuthorities() )
                .claim( "userId", convertedUser.getAppUserId())
                .setExpiration( new Date(System.currentTimeMillis()
                        + 15 * 60 * 1000) )
                .signWith( signingKey )
                .compact();
    }

    public UserDetails getUserFromToken( String authorizationHeader) {
        AppUser user = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String jwt = authorizationHeader.substring(7);

            Jws<Claims> tokenClaims = Jwts.parserBuilder()
                    .requireIssuer("MonsterBash")
                    .setSigningKey( signingKey)
                    .build()
                    .parseClaimsJws( jwt );

            user = new AppUser();

            user.setUsername(tokenClaims.getBody().getSubject());
            List<String> roles = new ArrayList<>();
            List<LinkedHashMap> authorities = tokenClaims.getBody().get("roles", List.class);
            for( LinkedHashMap<String,String> authority : authorities ){
                roles.add( authority.get("authority").substring(5));
            }

            user.setRoles( roles );
        }
        return user;
    }
}
