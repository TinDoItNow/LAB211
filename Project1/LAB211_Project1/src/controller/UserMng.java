package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import model.Status;
import model.User;

public class UserMng implements FunctionSystem {

    private ArrayList<User> usersList;
    private boolean userStatus;
    private boolean loginStatus;
    Scanner sc = new Scanner(System.in);

    public UserMng() {
    }

    public UserMng(String usersPath) throws Exception {
        usersList = getUserFromFile(usersPath);
    }

    public final ArrayList<User> getUserFromFile(String userPath) throws IOException {
        ArrayList<User> result = null;
        try {
            String thisLine;
            BufferedReader myInput;
            File usersFile = new File(userPath);
            String fullPath = usersFile.getAbsolutePath();
            FileInputStream userFile = new FileInputStream(fullPath);
            myInput = new BufferedReader(new InputStreamReader(userFile));
            User user;

            while ((thisLine = myInput.readLine()) != null) {
                if (!thisLine.contains("Username")) {

                    if (!thisLine.isEmpty()) {
                        String[] split = thisLine.split("\\|");
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        Status statusForAdd;
                        if (split[3].trim().equalsIgnoreCase("Enabled")) {
                            statusForAdd = Status.Enabled;
                        } else {
                            statusForAdd = Status.Disabled;
                        }
                        user = new User(split[0].trim(), split[1].trim(), split[2].trim(), statusForAdd);
                        result.add(user);
                        usersList = result;
                    }
                }
            }
            myInput.close();
        } finally {
            usersList = result;
        }
        return usersList;
    }

    public void printAllUser() {
        for (User toPrint : usersList) {
            System.out.println(toPrint);
        }
    }

    @Override
    public Properties readPropertiesFile() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\sourcefile\\config.properties"));
        } catch (IOException e) {
        }
        return properties;
    }

    public String checkAcc() {
        System.out.println("Your account name: ");
        String userName = sc.nextLine();
        System.out.println("Your password: ");
        String password = sc.nextLine();
        for (User userAcc : usersList) {
            if (userName.equals(userAcc.getUsername())) {
                if (password.equals(userAcc.getPassword())) {
                    return userName;
                }
            }
        }
        return "";
    }

    public String checkRole(String userName) {
        for (User user : usersList) {
            if (user.getUsername().compareTo(userName) == 0) {
                return user.getRole();
            }
        }
        return "";
    }

//    public String loginSystem() {
//        Menu mM = new Menu();
//        String userName, role;
//        do {
//            System.out.println("Please Login To System");
//            userName = checkAcc();
//            if (!userName.isEmpty()) {
//                System.out.println("Login success!!!");
//            } else {
//                System.out.println("Error! Try again!!!");
//            }
//        } while (userName.isEmpty());
//        role = checkRole(userName);
//        return role;
//    }

    public boolean logout() {
        int choice;
        System.out.println("Try Again With Another Account(1) or Exist(0)");
        do{
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    return true;
                case 0:
                    return false;
                default:
                    System.out.println("Please Choice Number 0 or 1");
                    break;
            }
        }while(choice != 0 && choice !=1);
        return true;
    }

}
