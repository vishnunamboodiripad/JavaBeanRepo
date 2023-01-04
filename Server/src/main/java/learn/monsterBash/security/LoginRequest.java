package learn.monsterBash.security;

public class LoginRequest {
    String username;
    String password;

    public String getUserName() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
