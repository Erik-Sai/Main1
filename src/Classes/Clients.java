package Classes;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Clients extends Employee {

    DBConnect dbConnect = new DBConnect();
    Connection connection = dbConnect.getConnection();
    String first = "SELECT * FROM client.mycredits";

    public static void clientMenu() {
        Scanner main = new Scanner(System.in);
        System.out.println("\nGreetings dear, Client!\n\n" +
                "Please dial the menu number to work with the program, if " +
                "finished, then dial 6:\n\n" +
                "1. Show a list of my credits\n" +
                "2. Show funds:\n" +
                "3. Buy currency:\n" +
                "4. Make a money transfer to an account:\n" +
                "5. View my transfer, which was made through the bank:\n" +
                "6. Exit\n"
        );
        System.out.print("Choose option: ");
        int menu_number = main.nextInt();

        if (menu_number == 1) {
            Clients first = new Clients();
            first.first();
            clientMenu();
        } else if (menu_number == 2) {
            Clients second = new Clients();
            second.second();
            clientMenu();
        } else if (menu_number == 3) {

            Clients third = new Clients();
            third.third();
            clientMenu();
        } else if (menu_number == 4) {

            Clients fourth = new Clients();
            fourth.fourth();
            clientMenu();
        } else if (menu_number == 5) {
            Clients five = new Clients();
            five.five();
            clientMenu();

        } else if (menu_number == 6) {
            Clients six = new Clients();
            six.six();
        } else {
            System.out.println("We didn't find function like this, please check the list of functions");
            menu();

        }

    }


    @Override
    public void first() {

        try {
            String query = "SELECT * FROM client.mycredits;";
            Statement st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(query);


            while (resultSet.next()) {

                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String credits = resultSet.getString("credits");
                String bank = resultSet.getString("bank");
                String date = resultSet.getString("date");
                System.out.printf("%s : %s :%s :%s :%s\n", id, name, credits, bank, date);
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

        try {
            Scanner in = new Scanner(System.in);
            Statement statement = connection.createStatement();

            System.out.println("Enter a currency which u want to see your funds");
            String currency = in.nextLine();

            if (Objects.equals(currency, "dollar")) {
                int id = 1;
                ResultSet receiver = statement.executeQuery("SELECT * FROM client.my_money WHERE idmy_money = " + id);
                int balance = 0;
                while (receiver.next()) {
                    balance = receiver.getInt("money") / 83;
                    System.out.println("For dollars: " + (balance));
                }

            } else if (Objects.equals(currency, "som")) {
                int id = 1;
                ResultSet receiver = statement.executeQuery("SELECT * FROM client.my_money WHERE idmy_money =" + id);
                int balance = 0;
                while (receiver.next()) {
                    balance = receiver.getInt("money");
                    System.out.println("For soms: " + (balance));
                }
            }


        } catch (Exception e) {
            System.out.println();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void third() {

        try {
            Scanner in = new Scanner(System.in);
            Statement statement = connection.createStatement();

            System.out.println("Enter currency");
            String currency = in.nextLine();

            System.out.println("Enter the amount you want to convert");
            int convert = in.nextInt();

            if (Objects.equals(currency, "dollar")) {
                int id = 1;
                ResultSet receiver = statement.executeQuery("SELECT * FROM client.my_money WHERE idmy_money = " + id);
                int balance = 0;
                while (receiver.next()) {
                    balance = receiver.getInt("money") + (convert * 83);
                    System.out.println((balance));
                }
                statement.execute("UPDATE client.my_money SET money = " + balance + " WHERE idmy_money = " + id);

            } else if (Objects.equals(currency, "som")) {
                int id = 1;
                ResultSet receiver = statement.executeQuery("SELECT * FROM client.my_money WHERE idmy_money =" + id);
                int balance = 0;
                while (receiver.next()) {
                    balance = receiver.getInt("money") + convert;
                    System.out.println(("New balance = " + balance));
                }
                statement.execute("UPDATE client.my_money SET money = " + balance + " WHERE idmy_money = " + id);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

        }

    }

    @Override
    public void five() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Please write sent to me or sent by me");
            String convention = in.nextLine();

            if (Objects.equals(convention, "sent_to_me")) {
                Statement statementSentToMe = connection.createStatement();
                ResultSet resultSet = statementSentToMe.executeQuery("SELECT * FROM client.sent_to_me;");
                while (resultSet.next()) {
                    int id = resultSet.getInt("idsent_to_me");
                    String recipient = resultSet.getString("recipient");
                    String sender = resultSet.getString("sender");
                    int money = resultSet.getInt("money");
                    System.out.println(id + ") " + recipient + ": " + sender + ": " + money);

                }
            } else if (Objects.equals(convention, "sent_by_me")) {
                Statement statementSentByMe = connection.createStatement();
                ResultSet resultSet = statementSentByMe.executeQuery("SELECT * FROM client.sent_by_me;");
                while (resultSet.next()) {
                    int id = resultSet.getInt("idsent_by_me");
                    String recipient = resultSet.getString("recipient");
                    String sender = resultSet.getString("sender");
                    int money = resultSet.getInt("money");
                    System.out.println(id + ") " + recipient + ":" + "      " + sender + ":" + "      " + money);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            System.out.println();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());

        }
    }

    @Override
    public void fourth() {

        try {
            Scanner in = new Scanner(System.in);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM client.name1;");

            while (resultSet.next()) {
                int id = resultSet.getInt("idName");
                String persons = resultSet.getString("persons");
                int money = resultSet.getInt("money");
                System.out.println(id + ") " + persons);
            }

            System.out.println("Select currency: ");
            String dollar = in.nextLine();

            System.out.println("Select your receiver");
            int you = in.nextInt();

            System.out.println("Select receiver: ");
            int idName = in.nextInt();

            System.out.println("Enter amount: ");
            int money = in.nextInt();


            if (Objects.equals(dollar, "dollar")) {
                ResultSet receiver1 = statement.executeQuery("SELECT * FROM client.name1 WHERE idName  = " + you);
                int balance1 = 0;
                while (receiver1.next()) {
                    balance1 = receiver1.getInt("money") - (money * 83);

                }
                statement.execute("UPDATE client.name1 SET money = " + balance1 + " WHERE idName = " + you);


                ResultSet receiver = statement.executeQuery("SELECT * FROM client.name1 WHERE idName  = " + idName);
                int balance = 0;
                while (receiver.next()) {
                    balance = receiver.getInt("money") + (money * 83);
                }
                statement.execute("UPDATE client.name1 SET money = " + balance + " WHERE idName = " + idName);


            } else if (Objects.equals(dollar, "som")) {
                ResultSet receiver1 = statement.executeQuery("SELECT * FROM client.name1 WHERE idName  = " + you);
                int balance1 = 0;
                while (receiver1.next()) {
                    balance1 = receiver1.getInt("money") - (money);

                }
                statement.execute("UPDATE client.name1 SET money = " + balance1 + " WHERE idName = " + you);


                ResultSet receiver = statement.executeQuery("SELECT * FROM client.name1 WHERE idName  = " + idName);
                int balance = 0;
                while (receiver.next()) {
                    balance = receiver.getInt("money") + (money);
                }
                statement.execute("UPDATE client.name1 SET money = " + balance + " WHERE idName = " + idName);
            }
        } catch (Exception e) {
            System.out.println();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void six() {

        try {

            System.out.println("The program is completed, we will be glad to see you back!");
        } catch (Exception e) {
            System.out.println();
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    }







