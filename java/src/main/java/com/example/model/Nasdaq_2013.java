package com.example.model;

import com.example.model.abs.Security;

import javax.persistence.*;

/**
 * Created by Kenneth on 2017/8/8.
 *
 */


@Entity
@Table(name = "nasdaq_2013")
public class Nasdaq_2013 extends Security {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "ticker")
    String name;

    int date;
    double open;
    double close;
    double high;
    double low;

    @Column(name = "vol")
    double volume;

    double hige;

    @Override
    public String toString() {
        return "Nasdaq_2013{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                ", hige=" + hige +
                '}';
    }

    public Nasdaq_2013() {
    }

    public Nasdaq_2013(String name, int date, double open, double close, double high, double low, double volume, double hige) {
        this.name = name;
        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.hige = hige;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
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

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getHige() {
        return hige;
    }

    public void setHige(double hige) {
        this.hige = hige;
    }
}
