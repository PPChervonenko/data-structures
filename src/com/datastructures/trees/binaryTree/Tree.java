package com.datastructures.trees.binaryTree;

public class Tree {
    private Node root;

    public void insertRecurs(int x) {
        root = doInsertRecurs(root, x);
    }

    private static Node doInsertRecurs(Node node, int x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.getKey()) {
            node.setLeft(doInsertRecurs(node.getLeft(), x));
        } else if (x > node.getKey()) {
            node.setRight(doInsertRecurs(node.getRight(), x));
        }
        return node;
    }
    public void insertNoRecurs(int x) {
        Node parent = null;
        Node node = root;
        while (node != null) {
            parent = node;
            if (x < node.getKey()) {
                node = node.getLeft();
            } else if (x > node.getKey()) {
                node = node.getRight();
            } else {
                return;
            }
        }

        Node newNode = new Node(x);

        if (parent == null) {
            root = newNode;
        } else if (x < parent.getKey()) {
            parent.setLeft(newNode);
        } else if (x > parent.getKey()) {
            parent.setRight(newNode);
        }
    }
}
