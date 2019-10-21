package com.datastructures.trees.genealogicalTree;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("Root");

        TreeNode<String> child1 = new TreeNode<>("Child1");
        child1.addChild("Grandchild1");
        child1.addChild("Grandchild2");

        TreeNode<String> child2 = new TreeNode<>("Child2");
        child2.addChild("Grandchild3");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild("Child3");

        root.addChildren(Arrays.asList(
                new TreeNode<>("Child4"),
                new TreeNode<>("Child5"),
                new TreeNode<>("Child6")
        ));

        for(TreeNode node : root.getChildren()) {
            System.out.println(node.getData());
        }
    }
}
