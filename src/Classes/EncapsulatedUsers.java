package Classes;

public class EncapsulatedUsers {

    private String login;
    private String password;
    private String first_name;
    private String last_name;
    private String type;

    public EncapsulatedUsers() {
    }

    public EncapsulatedUsers(String login, String password, String first_name, String last_name, String type) {
        this.login = (String) login;
        this.password = (String) password;
        this.first_name = (String) first_name;
        this.last_name = (String) last_name;
        this.type = (String) type;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getType() {
        return type;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
