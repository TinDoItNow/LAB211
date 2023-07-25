package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import model.Dealer;
import model.Status;
import viewer.Menu;

public class DealerMng implements FunctionDealer {

    public ArrayList<Dealer> dealersList;
    private Dealer feilds;
    Scanner sc = new Scanner(System.in);

    public DealerMng(String dealersPath) throws Exception {
        dealersList = getDealerFromFile(dealersPath);
    }

    public final ArrayList<Dealer> getDealerFromFile(String dealerPath) throws IOException {
        ArrayList<Dealer> result = null;
        try {
            String thisLine;
            BufferedReader myInput;
            File dealersFile = new File(dealerPath);
            String fullPath = dealersFile.getAbsolutePath();
            FileInputStream dealerFile = new FileInputStream(fullPath);
            myInput = new BufferedReader(new InputStreamReader(dealerFile));
            Dealer dealer;

            while ((thisLine = myInput.readLine()) != null) {
                if (!thisLine.contains("ID")) {
                    if (!thisLine.isEmpty()) {
                        String[] split = thisLine.split("\\|");
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        Status statusForAdd;
                        if (split[4].trim().equalsIgnoreCase("Enabled")) {
                            statusForAdd = Status.Enabled;
                        } else {
                            statusForAdd = Status.Disabled;
                        }
                        dealer = new Dealer(split[0].trim(), split[1].trim(), split[2].trim(), split[3].trim(), statusForAdd);
                        result.add(dealer);
                        dealersList = result;

                    }
                } else {
                    String[] split = thisLine.split("\\|");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    Status Status = null;
                    this.feilds = new Dealer(split[0].trim(), split[1].trim(), split[2].trim(), split[3].trim(), Status);
                }
            }
            myInput.close();
        } finally {
            dealersList = result;
        }
        return dealersList;
    }

    @Override
    public void addDealer() {
        String id, name, address, phoneNumber;
        int getStatus;
        String pattern = "^[D]{1}[0-9]{3}$";
        Status status = null;
        boolean idExisted = false;
        while (true) {
            System.out.print("Enter ID: ");
            id = sc.nextLine();
            for (Dealer checkID : dealersList) {
                if (checkID.getId().trim().equals(id) == true) {
                    System.out.println("This ID already exist!");
                    idExisted = true;
                    break;
                } else {
                    idExisted = false;
                }
            }
            if (idExisted == false && id.matches(pattern)) {
                break;
            } else if (!id.matches(pattern)) {
                System.out.println("Please Enter Input ID In The Correct Format |Dxxx With x is number|:");
            } else {
                System.out.println("Duplicate ID. Try with anther one!");
            }
        }
        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Address: ");
        address = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        phoneNumber = sc.nextLine();
        System.out.println("Choose Status");
        System.out.println("1 - Enable");
        System.out.println("2 - Disable");
        do {
            getStatus = Integer.parseInt(sc.nextLine());
            switch (getStatus) {
                case 1:
                    status = Status.Enabled;
                    break;
                case 2:
                    status = Status.Disabled;
                    break;
                default:
                    System.out.println("Please Choose 1 or 2");
                    break;
            }
        } while (getStatus != 1 && getStatus != 2);
        dealersList.add(new Dealer(id, name, address, phoneNumber, status));
        System.out.println("Add To Successful!");
    }

    @Override
    public void searchDealerByName() {
        String inputName;
        if (dealersList == null) {
            System.out.println("Empty List!");
        }
        System.out.println("Please!!! Enter Name To Search: ");
        inputName = sc.nextLine();
        for (Dealer dealList : dealersList) {
            if (dealList.getName().toUpperCase().trim().contains(inputName.toUpperCase())) {
                System.out.println(dealList);
            }
        }
    }

    @Override
    public void removeDealerByID() {
        ArrayList<Dealer> tempList = dealersList;
        String ID;
        if (dealersList == null) {
            System.out.println("Dealers profile doesn't have this ID");
        } else {
            System.out.print("Please!!! Enter ID to remove: ");
            ID = sc.nextLine();
            for (Dealer removeDealer : tempList) {
                if (removeDealer.getId().trim().equals(ID)) {
                    tempList.remove(removeDealer);
                    System.out.println("Congratulations on your successful remove!");
                    return;
                }
            }
            System.out.println("ID does not exist!");
            dealersList = tempList;
        }
    }

    @Override
    public void updateDealerByID() {
        String id, name, address, phoneNumber;
        int getStatus;
        String pattern = "^[D]{1}[0-9]{3}$";
        Status status = null;
        boolean idRight = false;
        boolean idExisted = false;
        do {
            System.out.print("Enter ID: ");
            id = sc.nextLine();
            if (!id.matches(pattern)) {
                System.out.println("Please Enter Input ID In The Correct Format |Dxxx With x is number|:");
                idRight = false;
            } else {
                idRight = true;
            }
            int count = 0;
            for (Dealer checkExistID : dealersList) {
                if (checkExistID.getId().trim().equals(id)) {
                    idExisted = true;
                    break;
                }
                count++;
                if (!checkExistID.getId().trim().equals(id)) {
                    if (count == dealersList.size()) {
                        System.out.println("This ID does not exist!");
                    }
                    idExisted = false;
                }
            }
        } while (idExisted == false || idRight == false);

        System.out.print("Enter Name: ");
        name = sc.nextLine();
        System.out.print("Enter Address: ");
        address = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        phoneNumber = sc.nextLine();
        System.out.println("Choose Status");
        System.out.println("1 - Enable");
        System.out.println("2 - Disable");
        do {
            getStatus = Integer.parseInt(sc.nextLine());
            switch (getStatus) {
                case 1:
                    status = Status.Enabled;
                    break;
                case 2:
                    status = Status.Disabled;
                    break;
                default:
                    System.out.println("Please Choose 1 or 2");
                    break;
            }
        } while (getStatus != 1 && getStatus != 2);
        int upDateDealer = getIndexByID(id);
        dealersList.set(upDateDealer, new Dealer(id, name, address, phoneNumber, status));
    }

    @Override
    public boolean saveForAll(String path, ArrayList<Dealer> list) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                for (Dealer temp : list) {
                    writer.write(temp.toString());
                    writer.write("\r\n");
                }
            }
            System.out.println("Successfully saved to the file");

        } catch (IOException e) {
            System.out.println("Error!!!");
            return false;
        }
        return true;
    }

    @Override
    public void printAllDealer() {
        String[] options = {"Print All", "Print Enabled", "Print Disabled"};
        System.out.println("Choose type of dealer to print: ");
        int choice = Menu.getChoice(options);
        switch (choice) {
            case 1:
                for (Dealer toPrint : dealersList) {
                    System.out.println(toPrint.toString());
                }
                break;
            case 2:
                for (Dealer toPrint : dealersList) {
                    if (toPrint.getStatus().equals(Status.Enabled)) {
                        System.out.println(toPrint.toString());
                    }
                }
                break;
            case 3:
                for (Dealer toPrint : dealersList) {
                    if (toPrint.getStatus().equals(Status.Disabled)) {
                        System.out.println(toPrint.toString());
                    }
                }
                break;
            default:
                System.out.println("Please Enter Input Again(1-2-3)!!!");
                break;
        }
    }

    public int getIndexByID(String inputID) {
        if (dealersList == null) {
            dealersList = new ArrayList<>();
        }
        int count = 0;
        for (Dealer compare : dealersList) {
            if (compare.getId().trim().equalsIgnoreCase(inputID.trim())) {
                break;
            }
            count++;
        }
        return count;
    }

    public ArrayList<Dealer> getDealersList() {
        return dealersList;
    }

}
