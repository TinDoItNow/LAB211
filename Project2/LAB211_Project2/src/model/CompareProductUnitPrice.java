package model;

import java.util.Comparator;

public class CompareProductUnitPrice<T extends Product> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        Product product1 = (Product) o1;
        Product product2 = (Product) o2;

        return Float.compare(product1.getUnitPrice(), product2.getUnitPrice());
    }
}
