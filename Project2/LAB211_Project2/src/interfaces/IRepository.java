package Interfaces;

import model.*;

public interface IRepository<T extends Product> {

    void Add(T product);

    void Delete(T product);

    void Print();

    void Sort();
}
