package ru.laptseu.bankApp.models;

public class Client {

    //тут мне все нравится
    private int id;
    private String name;
    private boolean isNaturalPerson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNaturalPerson() {
        return isNaturalPerson;
    }

    public void setNaturalPerson(boolean naturalPerson) {
        isNaturalPerson = naturalPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
