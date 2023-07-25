package model;

import java.util.Comparator;

public class CompareProductID<T extends Product> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        Product product1 = (Product) o1;
        Product product2 = (Product) o2;

        return product1.getProductID().compareTo(product2.getProductID());
    }
}
