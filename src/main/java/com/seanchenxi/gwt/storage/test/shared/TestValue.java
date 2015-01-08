package com.seanchenxi.gwt.storage.test.shared;

import java.io.Serializable;

/**
 * Created by Xi on 2015/1/8.
 */
public class TestValue implements Serializable {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
