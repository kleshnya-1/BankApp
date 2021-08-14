package ru.laptseu.bankapp.services;

public interface IMaintainableService {

    //я правильно делаю, что проше вернуть boolean значение,
    // чтоб проконтролировать можно было?
    boolean create();

    boolean read();

    boolean update();

    boolean delete();
}
