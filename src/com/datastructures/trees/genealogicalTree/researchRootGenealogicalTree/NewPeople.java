package com.datastructures.trees.genealogicalTree.researchRootGenealogicalTree;

import java.util.List;

public class NewPeople {

    private People parent;
    private List<People> children;


    public People getParent() {
        return parent;
    }

    public void setParent(People parent) {
        this.parent = parent;
    }

    public List<People> getChildren() {
        return children;
    }

    public void setChildren(List<People> children) {
        this.children = children;
    }
}
