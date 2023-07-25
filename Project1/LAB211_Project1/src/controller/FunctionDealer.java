package controller;

import java.util.ArrayList;
import model.Dealer;

public interface FunctionDealer {

    public void addDealer();

    public void searchDealerByName();

    public void removeDealerByID();

    public void updateDealerByID();

    public void printAllDealer();

    public boolean saveForAll(String path, ArrayList<Dealer> list);
}
