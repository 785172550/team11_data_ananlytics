package com.example.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

/**
 * Created by Kenneth on 2017/8/7.
 */

@Entity
@Table(name = "stock")
public class Stock extends Security{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    int time;
    int date;
    double open;
    double close;
    double high;
    double low;
    double volume;

    double splitfactor;
    double earnings;
    double dividends;

    @Column(name = "stockname")
    String name;


    public Stock() {

    }

    public Stock(int time, int date, double open, double close, double high, double low, double volume, String name) {
        this.time = time;
        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                ", splitfactor=" + splitfactor +
                ", earnings=" + earnings +
                ", dividends=" + dividends +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getSplitfactor() {
        return splitfactor;
    }

    public void setSplitfactor(double splitfactor) {
        this.splitfactor = splitfactor;
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public double getDividends() {
        return dividends;
    }

    public void setDividends(double dividends) {
        this.dividends = dividends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
