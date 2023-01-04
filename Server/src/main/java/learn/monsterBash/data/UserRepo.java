package learn.monsterBash.data;

import learn.monsterBash.models.AppUser;


public interface UserRepo {
    AppUser loadUserByUsername(String username);
}
