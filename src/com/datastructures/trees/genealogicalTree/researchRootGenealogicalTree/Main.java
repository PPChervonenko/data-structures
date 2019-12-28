package com.datastructures.trees.genealogicalTree.researchRootGenealogicalTree;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.addChildren(new People("root",Arrays.asList(
                new People("Child1", Arrays.asList(new People("Child 1.1"), new People("Child 1.2", Arrays.asList( new People("Child 1.1.1"), new People("Child 1.2"))), new People("Child 1.3"))),
                new People("Child2", Arrays.asList(new People(" Child2.1"), new People("Child2.2"), new People("Child2.3"))),
                new People("Child3", Arrays.asList(new People("Child3.1"), new People("Child3.2"), new People("Child3.3")))
        )));

        for(People node : tree.getChildren()) {

            System.out.println(node);
            System.out.println();
        }
        tree.searchRoot();
    }

}
