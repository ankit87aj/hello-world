package com.ramation.foldingcell.examples.collapsing;

/**
 * Created by Ankit on 28-05-2016.
 */
public class Flower
{
    String name;
    int id;

    public Flower(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
