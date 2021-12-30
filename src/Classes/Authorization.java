package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static Classes.Employee.menu;
import static Classes.Clients.clientMenu;


public class Authorization {

    static DBConnect connect = new DBConnect();
    static Connection connection = connect.getConnection();

    public static void accounts(String type) {
        if (type.equals("banker")) {
            menu();
        } else if (type.equals("client")) {
            clientMenu();
        }

    }

    public static void username_password() {
        Scanner main = new Scanner(System.in);
        System.out.println("\nWelcome to our program, if u wanna continue - please enter login and password!");
        System.out.println("");
        System.out.print("Enter login : ");
        String login = main.next();
        System.out.print("Enter Password: ");
        String password = main.next();

        String sqlLogin = "SELECT firstname,lastname,user_name,password,type " +
                "FROM erik1.user_account " +
                "WHERE user_name LIKE '" + login + "'";
        String checkLogin = "SELECT COUNT(user_name) FROM user_account WHERE user_name LIKE '" + login + "'";
        String checkPassword = "SELECT COUNT(user_name) FROM user_account WHERE password LIKE '" + password + "'";

        try {
            Statement statement1 = connection.createStatement();
            ResultSet resCheckLogin = statement1.executeQuery(checkLogin);
            Statement statement2 = connection.createStatement();
            ResultSet resCheckPassword = statement2.executeQuery(checkPassword);
            Statement statement3 = connection.createStatement();
            ResultSet appendToEncapsulatedUsers = statement3.executeQuery(sqlLogin);

            while (resCheckLogin.next()) {
                if (resCheckLogin.getInt(1) == 1) {
                    while (resCheckPassword.next()) {
                        if (resCheckPassword.getInt(1) == 1) {
                            while (appendToEncapsulatedUsers.next()) {

                                EncapsulatedUsers user = new EncapsulatedUsers();
                                user.setFirst_name(appendToEncapsulatedUsers.getString("firstname"));
                                user.setLast_name(appendToEncapsulatedUsers.getString("lastname"));
                                user.setLogin(appendToEncapsulatedUsers.getString("user_name"));
                                user.setPassword(appendToEncapsulatedUsers.getString("password"));
                                user.setType(appendToEncapsulatedUsers.getString("type"));

                                LoggedUsers loggedUsers = new LoggedUsers();
                                loggedUsers.logUser(user);

                                accounts(user.getType());

                            }
                            System.out.println(" ");
                        } else {
                        }
                    }
                } else {
                    System.out.println("\nInvalid login or password, please try again!");
                }
                username_password();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
