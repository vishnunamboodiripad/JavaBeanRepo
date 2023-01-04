package monsterBash.domain;

import monsterBash.data.UserRepo;
import monsterBash.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo repo;

    public UserDetails loadUserByUsername(String username) {
        AppUser matchingUser = repo.loadUserByUsername(username);

        if (matchingUser != null) {
            return matchingUser;
        }

        throw new UsernameNotFoundException("Could not find user with name: " + username);
    }
}
