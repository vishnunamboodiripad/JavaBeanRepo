package learn.monsterBash.data;

import learn.monsterBash.data.mappers.AppUserMapper;
import learn.monsterBash.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDatabaseRepo implements UserRepo {

    @Autowired
    JdbcTemplate template;

    public AppUser loadUserByUsername(String username) {
        List<String> roles = loadRolesByUsername(username);

        List<AppUser> matchingUsers = template.query("select * from App_User where username = ?;", new AppUserMapper(roles), username);

        if (matchingUsers.size() > 0) {
            return matchingUsers.get(0);
        }
        return null;
    }

    private List<String> loadRolesByUsername(String username) {
        String sql = "select username from \n" +
                "App_User as u\n" +
                "inner join App_Role_User as ur on u.user_id = ur.user_id\n" +
                "inner join App_Role as r on r.role_id = ur.role_id\n" +
                "where username = ?;";

        List<String> roles = template.query(sql, (rs, rowNum) -> rs.getString("username"), username);

        return roles;

    }
}
