package Classes;

public class LoggedUsers {

    private static EncapsulatedUsers loggedUsers;

    public static EncapsulatedUsers getLoggedUsers(){
        return loggedUsers;
    }

    public static void logUser(EncapsulatedUsers user){
        loggedUsers = user;
    }




}
