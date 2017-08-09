package com.example.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Kenneth on 2017/8/9.
 */

public abstract class Security {


    protected int id;

    protected String name;

    protected int date;
    protected double open;
    protected double close;
    protected double high;
    protected double low;
    protected double volume;

}
