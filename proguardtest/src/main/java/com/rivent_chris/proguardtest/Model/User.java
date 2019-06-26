package com.rivent_chris.proguardtest.Model;

import java.io.Serializable;

/**
 * Created by riven_chris on 2017/8/22.
 */

public class User implements Serializable {

    private static String group;

    public String country;

    private String name;
    private int age;
    private String height;

    public static String getGroup() {
        return group;
    }

    public static void setGroup(String group) {
        User.group = group;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
