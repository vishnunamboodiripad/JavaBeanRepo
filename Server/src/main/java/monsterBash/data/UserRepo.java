package monsterBash.data;

import monsterBash.model.AppUser;
import org.springframework.stereotype.Repository;


public interface UserRepo {
    AppUser loadUserByUsername(String username);
}
