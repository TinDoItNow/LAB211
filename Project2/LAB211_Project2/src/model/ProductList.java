package model;

import Interfaces.*;
import java.io.*;
import java.util.*;

public class ProductList<T extends Product> implements IRepository<T>, IFileOperation<T> {

    private ArrayList<T> productList = new ArrayList<>();

    public ArrayList<T> getProductList() {
        return productList;
    }

    public ProductList() {
        ReadFile();
    }

    @Override
    public void Add(T product) {
        productList.add(product);
    }

    @Override
    public void Delete(T product) {
        for (T item : productList) {
            if (item.equals(product)) {
                this.getProductList().remove(item);
                break;
            }
        }
    }

    @Override
    public void Print() {
        System.out.println("========== PRODUCT LIST ==========\n");
        for (T item : this.getProductList()) {
            Product tmp = (Product) item;
            System.out.println("Product ID: " + tmp.getProductID());
            System.out.println("Product Name: " + tmp.getProductName());
            System.out.format("Unit Price: %.2f\n", tmp.getUnitPrice());
            System.out.println("Quantity: " + tmp.getQuantity());
            System.out.println("Status: " + tmp.getStatus() + "\n");
        }
    }

    @Override
    public void Sort() {
        CompareProductID<T> compareFunction = new CompareProductID<T>();
        Collections.sort(productList, compareFunction);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void ReadFile() {
        try {
            File file = new File("src\\output\\Product.dat");
            if (file.createNewFile()) {
                System.out.println("\"Product.dat\" does not exist. Create new one successfully\n");
            }
            String f = file.getAbsolutePath();
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineRead = bufferedReader.readLine();

            while (lineRead != null) {
                String[] lineParameters = lineRead.split(";");
                Product product = new Product();
                product.setProductID(lineParameters[0]);
                product.setProductName(lineParameters[1]);
                product.setUnitPrice(Float.parseFloat(lineParameters[2]));
                product.setQuantity(Integer.parseInt(lineParameters[3]));
                product.setStatus(lineParameters[4]);
                productList.add((T) product);
                lineRead = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error occured while reading \"Product.dat\" . Exiting...\n");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Wrong number format. Check \"Product.dat\" file again. Exiting...\n");
            System.exit(1);
        }
    }

    @Override
    public final void SaveFile() {
        CompareProductID<T> function = new CompareProductID<>();
        getProductList().sort(function);
        try {
            File file = new File("src\\output\\Product.dat");
            if (file.createNewFile()) {
                System.err.println("No \"Product.dat\" to save to. Successfully create one\n");
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            for (T item : getProductList()) {
                Product tmp = (Product) item;
                String line = String.format("%s;%s;%.2f;%d;%s\n",
                        tmp.getProductID(),
                        tmp.getProductName(),
                        tmp.getUnitPrice(),
                        tmp.getQuantity(),
                        tmp.getStatus());
                bufferWriter.write(line);
            }
            System.out.println("Save successfully!\n");
            bufferWriter.close();
        } catch (IOException e) {
            System.err.println("Can't save to file \"Product.dat\". Check the file again\n");
        }
    }
}
