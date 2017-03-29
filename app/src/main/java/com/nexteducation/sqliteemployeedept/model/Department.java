package com.nexteducation.sqliteemployeedept.model;

/**
 * Created by next on 24/3/17.
 */
public class Department
{
    int id;
    String name;
    int headCount;

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

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }
}
