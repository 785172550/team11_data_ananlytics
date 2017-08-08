package com.example.model;

import javax.persistence.*;

/**
 * Created by Kenneth on 2017/8/8.
 *
 */
@Entity
@Table(name = "forex_2013")
public class Forex_2013 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "ticker")
    String name;

    int date;
    double open;
    double close;
    double hige;
    double low;
    double vol;

}
