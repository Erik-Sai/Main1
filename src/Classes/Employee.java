package Classes;

import Interfaces.Stuff;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class Employee implements Stuff  {

    DBConnect dbConnect = new DBConnect();
    Connection connection = dbConnect.getConnection();

    String getMax = "SELECT credits as max_credits, clients_name FROM clients ORDER BY credits DESC LIMIT 1";
    String getMin = "SELECT MIN(credits) as min_credits, clients_name FROM clients";
    String seven = "SELECT * FROM client.sum_convention";

    public static void menu() {
        Scanner main = new Scanner(System.in);
        System.out.println("\nPlease dial the menu number to work with the program, if you have finished, then dial 8\n" +"\n"+
                "1. Show customer list\n" +
                "2. Find a client:\n" +
                "3. Show the name of the client who took the maximum loan amount:\n" +
                "4. Show the name of the client who took the minimum loan amount:\n" +
                "5. Set a new amount for conversion:\n" +
                "6. Calculate the amount of transfers:\n" +
                "7. Exit \n\n");
        System.out.print("Choose option: ");
        int menu_number = main.nextInt();

        if (menu_number == 1){
            Employee first = new Employee();
            first.first();
            menu();
        }

        else if (menu_number == 2){
            Employee second = new Employee();
            second.second();
            menu();
        }
        else if (menu_number == 3){

            Employee third = new Employee();
            third.third();
            menu();

        }

        else if (menu_number == 4){

            Employee fourth = new Employee();
            fourth.fourth();
            menu();

        }

        else if (menu_number == 5){

            Employee five = new Employee();
            five.five();
            menu();

        }

        else if (menu_number == 6){
            Employee six = new Employee();
            six.six();
            menu();

        }
        else if (menu_number == 7){
            Employee seven = new Employee();
            seven.seventh();

        }
        else {
            System.out.println("We didn't find function like this");
            menu();

        }


    }

    @Override
    public void first() {

        try {
            String query = "SELECT * FROM erik1.info";
            Statement st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(query);

            while (resultSet.next()) {

                String id = resultSet.getString("id");
                String fName = resultSet.getString("Fname");
                String lName = resultSet.getString("Lname");
                String bDate = resultSet.getString("Bdate");
                String address = resultSet.getString("Address");
                String sex = resultSet.getString("Sex");
                String phoneNumber = resultSet.getString("PhoneNumber");
                System.out.printf("%s : %s :%s: %s : %s :%s\n", id, fName, lName, bDate, address, sex, phoneNumber);
            }

        } catch (SQLException e) {
            System.out.println();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void second() {
        Scanner in = new Scanner(System.in);
        System.out.print("1) Find a client\n");
        System.out.print("Enter customer name: ");
        try {
            Statement st = connection.createStatement();
            String name1 = in.nextLine();
            String search = "select * from erik1.banker where client_name='" + name1 + "'";
            ResultSet resultSet = st.executeQuery(search);
            while (resultSet.next()) {
                int idbanker = resultSet.getInt("idbanker");
                String client_name = resultSet.getString("client_name");
                int credits = resultSet.getInt("credits");
                System.out.printf("%d : %s : %d\n", idbanker, client_name, credits);
            }
        } catch (SQLException e) {
            System.out.println();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void third() {

        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(getMax);

            while (resultSet.next()) {
                String client_name = resultSet.getString("clients_name");
                int max_credits = resultSet.getInt("max_credits");
                System.out.println(":" + client_name + ":" + max_credits);
            }

        } catch (SQLException e) {
            System.out.println();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    @Override
    public void fourth() {
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(getMin);

            while (resultSet.next()) {
                String client_name = resultSet.getString("clients_name");
                int min_credits = resultSet.getInt("min_credits");
                System.out.printf(" %s : %d\n", client_name, min_credits);
            }

        } catch (SQLException e) {
            System.out.println();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void five() {

        System.out.println("Set a new amount for conversations");
        Scanner main2 = new Scanner(System.in);
        System.out.println("1. For dollar\n" +
                "2. For som:\n");
        String sum = main2.nextLine();
        if (sum.equals("1")) {
            try {
                Scanner in = new Scanner(System.in);
                String query = "SELECT * FROM erik1.convention;";
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("idconvention");
                    int dollar = resultSet.getInt("dollar");
                    int som = resultSet.getInt("som");

                }
                System.out.println("Enter id: ");
                int idName = in.nextInt();
                System.out.println("Enter amount: ");
                int money = in.nextInt();

                ResultSet receiver = st.executeQuery("SELECT * FROM erik1.convention WHERE idconvention = " + idName);
                int balance = 0;
                while (receiver.next()) {
                    balance = receiver.getInt("dollar") * 0 + (money);
                    System.out.println("The conversion was successful, current value = " + balance);
                }
                String conv = "UPDATE erik1.convention SET dollar = " + balance + " WHERE idconvention = " + idName;
                st.execute(conv);
            } catch (SQLException e) {
                System.out.println();
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        } else if (sum.equals("2")) {
            try {
                Scanner in = new Scanner(System.in);
                String query = "SELECT * FROM erik1.convention;";
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("idconvention");
                    int dollar = resultSet.getInt("dollar");
                    int som = resultSet.getInt("som");

                }
                System.out.println("Enter id: ");
                int idName = in.nextInt();
                System.out.println("Enter amount: ");
                int money = in.nextInt();
                ResultSet receiver = st.executeQuery("SELECT * FROM erik1.convention WHERE idconvention = " + idName);
                int balance = 0;
                while (receiver.next()) {
                    balance = receiver.getInt("som") * 0 + (money);
                    System.out.println("The conversion was successful, current value = " + balance);
                }
                String conv = "UPDATE erik1.convention SET som = " + balance + " WHERE idconvention = " + idName;
                st.execute(conv);
            } catch (SQLException e) {
                System.out.println();
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void six() {
        Scanner main2 = new Scanner(System.in);
        System.out.println("a. For each month of the current year\n" +
                "b. For the quarter:\n" +
                "c. During all this time:\n" +
                "d. Enter city or country:\n");
        System.out.println("Calculate the amount of transfers: ");
        String sum = main2.nextLine();
        if (sum.equals("a")) {
            try {
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(seven);

                while (resultSet.next()) {
                    int month1 = resultSet.getInt("month1");
                    System.out.printf("%d\n", month1);
                }
            } catch (SQLException e) {
                System.out.println();
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        } else if (sum.equals("b")) {
            try {
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(seven);

                while (resultSet.next()) {
                    int cvartal = resultSet.getInt("cvartal");
                    System.out.printf("%d", cvartal);
                }

            }catch (SQLException e) {
                System.out.println();
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        } else if (sum.equals("c")) {
            try {
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(seven);

                while (resultSet.next()) {
                    int time1 = resultSet.getInt("time1");
                    System.out.printf("%d", time1);
                }

            }catch (SQLException e) {
                System.out.println();
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        } else if (sum.equals("d")) {
            System.out.println("Enter city or country: ");
            String sum1 = main2.nextLine();
            if (sum1.equals("kyrgyzstan")) {
                try {
                    Statement st = connection.createStatement();
                    ResultSet resultSet = st.executeQuery(seven);
                    while (resultSet.next()) {
                        int kyrgyzstan = resultSet.getInt("kyrgyzstan");
                        System.out.printf("%d", kyrgyzstan);
                    }
                } catch (SQLException e) {
                    System.out.println();
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            } else if (sum1.equals("russia")) {
                try {
                    Statement st = connection.createStatement();
                    ResultSet resultSet = st.executeQuery(seven);

                    while (resultSet.next()) {
                        int russia = resultSet.getInt("russia");
                        System.out.printf("%d", russia);
                    }
                } catch (SQLException e) {
                    System.out.println();
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            } else if (sum1.equals("usa")) {
                try {
                    Statement st = connection.createStatement();
                    ResultSet resultSet = st.executeQuery(seven);

                    while (resultSet.next()) {
                        int usa = resultSet.getInt("usa");
                        System.out.printf("%d", usa);
                    }
                } catch (Exception e) {
                    System.out.println();
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void seventh() {

        try {

            System.out.println("The program is completed, we will be glad to see you back!");
        } catch (Exception e) {
            System.out.println();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

}



