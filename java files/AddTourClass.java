package com.example.toursandtravelfinal;
public class AddTourClass {
    String name, cost, time, iteneary,image;

    public AddTourClass(String name, String cost, String time, String iteneary,String image) {
        this.name = name;
        this.cost = cost;
        this.time = time;
        this.iteneary = iteneary;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcost() {
        return cost;
    }

    public void setcost(String cost) {
        this.cost = cost;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public String getiteneary() {
        return iteneary;
    }

    public void setiteneary(String iteneary) {
        this.iteneary = iteneary;
    }
}
