package com.ps.boj.p5639;

import java.io.*;
import java.nio.file.*;

class Node {
    int val;
    Node parent;
    Node left;
    Node right;

    Node(int val) {
        this.val = val;
    }
}

class BinarySearchTree {
    Node root;

    BinarySearchTree(Node root) {
        this.root = root;
    }

    public void add(Node node) {
        addTo(this.root, node);
    }

    public void addTo(Node node, Node newNode) {
        if (node.val < newNode.val) {
            if (node.right == null) {
                node.right = newNode;
            } else {
                addTo(node.right, newNode);
            }
        } if (node.val > newNode.val) {
            if (node.left == null) {
                node.left = newNode;
            } else {
                addTo(node.left, newNode);
            }
        }
    }

    public void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }

    public void postOrderFromRoot() {
        postOrder(this.root);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p5639/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int root = Integer.parseInt(br.readLine());
        Node rootNode = new Node(root);
        BinarySearchTree bst = new BinarySearchTree(rootNode);
        String line;
        while ((line = br.readLine()) != null) {
            Node curr = new Node(Integer.parseInt(line));
            bst.add(curr);
        }
        bst.postOrderFromRoot();
    }
}
