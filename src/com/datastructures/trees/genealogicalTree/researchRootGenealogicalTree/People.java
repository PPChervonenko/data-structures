package com.datastructures.trees.genealogicalTree.researchRootGenealogicalTree;

import java.util.List;

public class People <T>{

    private String name;
    private List<People> children;

    public People(String s) {
        this.name = s;
    }

    public <T> People(String s, List<People> asList) {
        this.name = s;
        this.children = asList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<People> getChildren() {
        return children;
    }

    public void setChildren(List<People> children) {
        this.children = children;
    }

    @Override
    public java.lang.String toString() {
        return "People{" +
                "name=" + name +
                ", children=" + children +
                '}';
    }
}
