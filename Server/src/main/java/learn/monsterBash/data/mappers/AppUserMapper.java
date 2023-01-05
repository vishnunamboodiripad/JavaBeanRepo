package learn.monsterBash.data.mappers;

import learn.monsterBash.models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppUserMapper implements RowMapper<AppUser> {
    List<String> userRoles;

    public AppUserMapper( List<String> userRoles){
        this.userRoles = userRoles;
    }

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUser toReturn = new AppUser();

        toReturn.setAppUserId( rs.getInt("user_id") );
        toReturn.setUsername( rs.getString("username"));
        toReturn.setEnabled(rs.getBoolean("enabled"));
        toReturn.setPassHash(rs.getString("password"));

        toReturn.setRoles(userRoles);

        return toReturn;
    }
}
