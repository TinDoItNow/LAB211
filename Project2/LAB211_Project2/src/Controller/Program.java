package Controller;

import Viewer.Menu;
import model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Program {

    static Scanner scannerObj = new Scanner(System.in);

    public static void main(String[] args) {
        ProductList<Product> list = new ProductList<>();
        Menu menuList = new Menu();
        menuList.SetMenu();
        while (true) {
            for (String command : menuList) {
                System.out.println(command);
            }
            System.out.print("\nPlease enter your option: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scannerObj.nextLine());
            } catch (Exception e) {
                System.err.println("Invalid input\n");
            }
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.flush();
                    PrintProduct(list);
                    break;
                case 2:
                    System.out.flush();
                    AddProduct(list, CreateProduct(list, "new"));
                    break;
                case 3:
                    System.out.flush();
                    CheckProduct(list);
                    break;
                case 4:
                    System.out.flush();
                    SearchProductName(list);
                    break;
                case 5: System.out.flush();{
                    if (list.getProductList().isEmpty()) {
                        System.out.println("Has no any Product\n");
                        break;
                    }
                    System.out.println("    1. Update Product");
                    System.out.println("    2. Delete Product");
                    System.out.print("    Which one do you want? ");
                    int tmp;
                    try {
                        tmp = Integer.parseInt(scannerObj.nextLine());
                        System.out.println();
                        switch (tmp) {
                            case 1:
                                UpdateProduct(list);
                                break;
                            case 2:
                                DeleteProduct(list);
                                break;
                            default: {
                                System.out.println("Invalid input\n");
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Invalid input\n");
                        break;
                    }
                }
                case 6:
                    System.out.flush();
                    list.SaveFile();
                    break;
                case 7:
                    System.out.flush();
                    PrintList(list);
                    break;
                default:
                    System.out.println("Exiting program...");
                    System.exit(0);
            }
            if (backMenu() == false) {
                return;
            }
        }
    }

    private static void PrintProduct(ProductList<Product> list) {
        if (list.getProductList().isEmpty()) {
            System.out.println("Have no any Product\n");
            return;
        }
        String input;
        try {
            System.out.print("Enter Product ID: ");
            input = scannerObj.nextLine();
            if (!CheckProductID(input)) {
                return;
            }
            System.out.println();
            for (Product item : list.getProductList()) {
                if (item.getProductID().equals(input)) {
                    item.Print();
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Invalid input\n");
        }
    }

    private static Product CreateProduct(ProductList<Product> list, String type) {
        Product product = new Product();
        while (true) // read product id
        {
            System.out.print("Enter Product ID: ");
            try {
                product.setProductID(scannerObj.nextLine());
                if (type.equals("update") && product.getProductID().isEmpty()) {
                    product.setProductID(null);
                    System.out.println();
                    break;
                } else if (CheckProductID(product.getProductID())) {
                    if (CheckProductAlreadyExist(list, product.getProductID())) {
                        System.out.println("Already exist\n");
                        continue;
                    }
                    System.out.println();
                    break;
                }
            } catch (Exception e) {
                System.err.println("Invalid input\n");
            }
        }

        while (true) // read product name
        {
            System.out.print("Enter Product Name: ");
            try {
                product.setProductName(scannerObj.nextLine());
                if (type.equals("update") && product.getProductName().isEmpty()) {
                    product.setProductName(null);
                    System.out.println();
                    break;
                } else if (CheckProductName(product.getProductName())) {
                    System.out.println();
                    break;
                }
            } catch (Exception e) {
                System.err.println("Invalid input\n");
            }
        }

        while (true) // read price
        {
            System.out.print("Enter Unit Price: ");
            String tmp;
            try {
                tmp = scannerObj.nextLine();
                if (type.equals("update") && tmp.isEmpty()) {
                    product.setUnitPrice(-1.0f);
                    System.out.println();
                    break;
                }
                product.setUnitPrice(Float.parseFloat(tmp));
                if (CheckUnitPrice(product.getUnitPrice())) {
                    System.out.println();
                    break;
                }
            } catch (Exception e) {
                System.err.println("Invalid input (must be in range 0..10000\n");
            }
        }

        while (true) // read quantity
        {
            System.out.print("Enter Quantity: ");
            String tmp;
            try {
                tmp = scannerObj.nextLine();
                if (type.equals("update") && tmp.isEmpty()) {
                    product.setQuantity(-1);
                    System.out.println();
                    break;
                }
                product.setQuantity(Integer.parseInt(tmp));
                if (CheckQuantity(product.getQuantity())) {
                    System.out.println();
                    break;
                }
            } catch (Exception e) {
                System.err.println("Invalid input (must be in range 0..1000\n");
            }
        }

        while (true) // read status
        {
            System.out.println("1. Available");
            System.out.println("2. Not Available");
            System.out.print("Enter Status: ");
            String tmp;
            try {
                tmp = scannerObj.nextLine();
                if (type.equals("update") && tmp.isEmpty()) {
                    System.out.println();
                    break;
                }
                switch (Integer.parseInt(tmp)) {
                    case 1:
                        product.setStatus("Available");
                        break;
                    case 2:
                        product.setStatus("Not Available");
                        break;
                    default:
                        System.err.println("Not in range 0..1\n");
                }
                if (product.getStatus() != null) {
                    System.out.println();
                    break;
                }
            } catch (Exception e) {
                System.err.println("Invalid input\n");
            }
        }
        product.Print();
        return product;
    }

    private static void AddProduct(ProductList<Product> list, Product product) {
        while (true) // save product?
        {
            System.out.print("Save the product (y/n)? ");
            String choice;
            try {
                choice = scannerObj.nextLine().toLowerCase();
                if (choice.equals("y") || choice.equals("yes")) {
                    list.Add(product);
                    System.out.println();
                    break;
                } else if (choice.equals("n") || choice.equals("no")) {
                    System.out.println();
                    break;
                } else {
                    System.out.println("Invalid input\n");
                }
            } catch (Exception e) {
                System.err.println("Invalid input\n");
            }
        }
    }

    private static void CheckProduct(ProductList<Product> list) {
        if (list.getProductList().isEmpty()) {
            System.out.println("Have no any Product\n");
            return;
        }
        String input;
        try {
            System.out.print("Enter the Product Name: ");
            input = scannerObj.nextLine();
        } catch (Exception e) {
            System.err.println("Invalid input\n");
            return;
        }
        for (Product item : list.getProductList()) {
            if (item.getProductName().equals(input)) {
                System.out.println("Exist Product\n");
                item.Print();
                return;
            }
        }
        System.out.println("No Product Found!\n");
    }

    private static void SearchProductName(ProductList<Product> list) {
        if (list.getProductList().isEmpty()) {
            System.out.println("Have no any Product\n");
            return;
        }
        ArrayList<Product> listProductName = new ArrayList<>();
        String input;
        try {
            System.out.print("Enter the search name: ");
            input = scannerObj.nextLine().toLowerCase();
        } catch (Exception e) {
            System.err.println("Invalid input\n");
            return;
        }
        System.out.println();
        for (Product item : list.getProductList()) {
            if (item.getProductName().toLowerCase().contains(input)) {
                listProductName.add(item);
            }
        }
        CompareProductName<Product> function = new CompareProductName<>();
        listProductName.sort(function);
        for (Product item : listProductName) {
            item.Print();
        }
    }

    private static void UpdateProduct(ProductList<Product> list) {
        String input;
        try {
            System.out.print("Enter Product ID: ");
            input = scannerObj.nextLine();
            if (input.isEmpty()) {
                System.out.println("Cancel updating...\n");
                return;
            } else if (!CheckProductID(input)) {
                return;
            }
            System.out.println();
            for (Product item : list.getProductList()) {
                if (item.getProductID().equals(input)) {
                    System.out.println("========== EDIT PRODUCT ==========\n");
                    Product product = CreateProduct(list, "update");
                    if (product.getProductID() == null) {
                        product.setProductID(item.getProductID());
                    }
                    if (product.getProductName() == null) {
                        product.setProductName(item.getProductName());
                    }
                    if (product.getUnitPrice() < 0) {
                        product.setUnitPrice(item.getUnitPrice());
                    }
                    if (product.getQuantity() < 0) {
                        product.setQuantity(item.getQuantity());
                    }
                    if (product.getStatus() == null) {
                        product.setStatus(item.getStatus());
                    }
                    list.getProductList().set(list.getProductList().indexOf(item), product);
                    System.out.println("Update successfully!\n");
                    return;
                }
            }
            System.out.println("Product Name does not exist");
            System.out.println("Update failed\n");
        } catch (Exception e) {
            System.err.println("Invalid input\n");
        }
    }

    private static void DeleteProduct(ProductList<Product> list) {
        String input;
        try {
            System.out.print("Enter Product ID: ");
            input = scannerObj.nextLine();
            if (!CheckProductID(input)) {
                return;
            }
            for (Product item : list.getProductList()) {
                if (item.getProductID().equals(input)) {
                    list.Delete(item);
                    System.out.println("Remove successfully!\n");
                    return;
                }
            }
            System.out.println("Product Name does not exist");
            System.out.println("Remove failed\n");
        } catch (Exception e) {
            System.err.println("Invalid input\n");
        }
    }

    private static void PrintList(ProductList<Product> list) {
        if (list.getProductList().isEmpty()) {
            System.out.println("Has no any Product\n");
            return;
        }
        CompareProductQuantity<Product> function = new CompareProductQuantity<>();
        list.getProductList().sort(function.reversed());
        int subIndex = 0;
        CompareProductUnitPrice<Product> functionTmp = new CompareProductUnitPrice<>();
        for (Product item : list.getProductList()) {
            if (item.getQuantity() != list.getProductList().get(subIndex).getQuantity()) {
                try {
                    int tmp = list.getProductList().indexOf(item);
                    Collections.sort(list.getProductList().subList(subIndex, tmp), functionTmp);
                } catch (IndexOutOfBoundsException e) {
                }
                subIndex = list.getProductList().indexOf(item);
            }
        }
        list.Print();
    }

    private static boolean CheckProductID(String ProductID) {
        if (ProductID.matches("^PRO\\d{4}$")) {
            return true;
        } else {
            System.out.println("Wrong format (Must be PRO**** with * represents any number digit)\n");
            return false;
        }
    }

    private static boolean CheckProductName(String ProductName) {
        if (ProductName.length() >= 5 && ProductName.contains(" ") == false) {
            return true;
        } else {
            System.out.println("Wrong name format (Must has at least 5 characters and no space)\n");
            return false;
        }
    }

    private static boolean CheckUnitPrice(float UnitPrice) {
        if (0 <= UnitPrice && UnitPrice <= 10000.0f) {
            return true;
        } else {
            System.out.println("Unit Price must in range 0..10000\n");
            return false;
        }
    }

    private static boolean CheckQuantity(int Quantity) {
        if (0 <= Quantity && Quantity <= 1000) {
            return true;
        } else {
            System.out.println("Quantity must in range 0..1000\n");
            return false;
        }
    }

    private static boolean CheckProductAlreadyExist(ProductList<Product> list, String ProductID) {
        for (Product item : list.getProductList()) {
            if (item.getProductID().equals(ProductID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean backMenu() {
        int choice;
        System.out.println("1.Back To Menu ");
        System.out.println("2.Exit ");
        System.out.print("Please Choose your option: ");
        do {
            choice = Integer.parseInt(scannerObj.nextLine());
            switch (choice) {
                case 1:
                    return true;
                case 2:
                    System.out.println("Exiting program...");
                    return false;
                default:
                    System.out.println("Please Choice Number 1 or 2!");
                    break;
            }
        } while (choice != 1 && choice != 2);
        return true;
    }
}
