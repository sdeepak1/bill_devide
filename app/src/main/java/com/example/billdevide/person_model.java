package com.example.billdevide;

public class person_model {
    String name;
    int old_reading, new_reading, consume_unit, total_amount;
    float unit_cost;

    public person_model(String name, int old_reading, int new_reading, int consume_unit, int total_amount, float unit_cost) {
        this.name = name;
        this.old_reading = old_reading;
        this.new_reading = new_reading;
        this.consume_unit = consume_unit;
        this.total_amount = total_amount;
        this.unit_cost = unit_cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld_reading() {
        return old_reading;
    }

    public void setOld_reading(int old_reading) {
        this.old_reading = old_reading;
    }

    public int getNew_reading() {
        return new_reading;
    }

    public void setNew_reading(int new_reading) {
        this.new_reading = new_reading;
    }

    public int getConsume_unit() {
        return consume_unit;
    }

    public void setConsume_unit(int consume_unit) {
        this.consume_unit = consume_unit;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public float getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(float unit_cost) {
        this.unit_cost = unit_cost;
    }
}