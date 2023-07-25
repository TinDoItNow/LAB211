package viewer;

import controller.DealerMng;
import controller.UserMng;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
//         Menu options
        final String fileDealers = "src\\model\\dealers.csv";
        final String fileUsers = "src\\input\\users.csv";
        DealerMng dMng = new DealerMng(fileDealers);
        UserMng uMng = new UserMng(fileUsers);
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        boolean loginAg = true;
        do {
            System.out.println("\n-----------------DEALER-----------------");
            System.out.println("***----------------------------------***");
            System.out.println("Login To System: ");
            String userName = uMng.checkAcc();
            if (!userName.isEmpty()) {
                System.out.println("Login success!!!");
            } else {
                System.out.println("Error! Try again!!!");
                continue;
            }
            System.out.println("Your role is: " + uMng.checkRole(userName));
            String role = uMng.checkRole(userName);
            if (role.equals("R001")) {
                String[] options = {"Add New Dealer",
                    "Search a Dealer", "Remove a Dealer",
                    "Update a Dealer", "Print All Dealer",
                    "Write to file", "Logout"};
                System.out.println("Select your options: ");
                do {
                    choice = Menu.getChoice(options); // show Menu options
                    switch (choice) {
                        case 1:
                            System.out.flush();
                            dMng.addDealer();
                            break;
                        case 2:
                            System.out.flush();
                            dMng.searchDealerByName();
                            break;
                        case 3:
                            System.out.flush();
                            dMng.removeDealerByID();
                            break;
                        case 4:
                            System.out.flush();
                            System.out.println("Enter ID for update: ");
                            dMng.updateDealerByID();
                            break;
                        case 5:
                            System.out.flush();
                            dMng.printAllDealer();
                            break;
                        case 6:
                            System.out.flush();
                            dMng.saveForAll("src\\output\\dealers.csv", dMng.dealersList);
                            break;
                        case 7:
                            if (!uMng.logout()) {
                                return;
                            }
                            break;
                        default:
                            System.out.println("Please choose your option from 1-7");
                    }
                } while (choice >= 1 && choice < 7);
            } else {
                System.out.println("Your role is not updated in this system!");
                String[] options = {"Logout"};
                choice = Menu.getChoice(options);
                if (choice == 1) {
                    loginAg = uMng.logout();
                }
            }
        } while (loginAg);
    }
}
