package Interfaces;

import model.Product;

public interface IFileOperation<T extends Product> {

    void ReadFile();

    void SaveFile();
}
