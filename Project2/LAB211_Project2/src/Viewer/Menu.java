package Viewer;

import Interfaces.*;
import java.util.ArrayList;

public class Menu extends ArrayList<String> implements IMenu {

    public Menu() {
        super();
    }

    @Override
    public void SetMenu() {
        this.add("========== MENU ==========\n");
        this.add("1.        Print");
        this.add("2.        Create a Product");
        this.add("3.        Check exits Product");
        this.add("4.        Search Product's information by Name");
        this.add("5.        Update Product");
        this.add("6.        Save Products to file");
        this.add("7.        Print list Products from file");
        this.add("Others.   Quit");
    }

    @Override
    public void ShowMenu() {
        for (String command : this) {
            System.out.println(command);
        }
    }
}
