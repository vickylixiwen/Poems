package com.example.poems;

public class WrongList {
    public int id;
    private String wrongItem;

    public static final WrongList[] wrongLists = {
          new WrongList(1, "\tWho are you?\n\t I'm Dora."),
            new WrongList(2, "\tWho am I?\n\t You are Alice."),
            new WrongList(3, "\tI don't like tea"),
            new WrongList(4, "\tJeff doesn't like tea"),
            new WrongList(5, "\tWho am I?\t You are Alice."),
            new WrongList(6, "\tWho am I?\t You are Alice."),
            new WrongList(7, "\tWho am I?\t You are Alice."),
            new WrongList(8, "\tWho am I?\t You are Alice."),
            new WrongList(9, "\tWho am I?\t You are Alice."),
            new WrongList(10, "\tWho am I?\t You are Alice."),
            new WrongList(11, "\tWho am I?\t You are Alice."),
            new WrongList(12, "\tWho am I?\t You are Alice."),
            new WrongList(13, "\tWho am I?\t You are Alice."),
            new WrongList(14, "\tWho am I?\t You are Alice."),
            new WrongList(15, "\tWho am I?\t You are Alice."),
            new WrongList(16, "\tWho am I?\t You are Alice."),
            new WrongList(17, "\tWho am I?\t You are Alice."),
            new WrongList(18, "\tWho am I?\t You are Alice."),
            new WrongList(19, "\tWho am I?\t You are Alice."),
            new WrongList(20, "\tWho am I?\t You are Alice."),
            new WrongList(21, "\tWho am I?\t You are Alice."),
            new WrongList(22, "\tWho am I?\t You are Alice."),
            new WrongList(23, "\tWho am I?\t You are Alice."),
            new WrongList(24, "\tWho am I?\t You are Alice."),
            new WrongList(25, "\tWho am I?\t You are Alice.")
    };

    public WrongList(int id, String wrongItem) {
        this.id = id;
        this.wrongItem = wrongItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWrongItem() {
        return wrongItem;
    }

    public void setWrongItem(String wrongItem) {
        this.wrongItem = wrongItem;
    }

    @Override
    public String toString() {
        return id + "\t" + wrongItem ;
    }
}
