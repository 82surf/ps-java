package com.ps.boj.p1991;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Node {
    char value;
    Node left;
    Node right;

    Node(char value) {
        this.value = value;
    }
}

public class Main {
    // 전위 순회
    public static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    // 중위 순회
    public static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value);
        inorder(node.right);
    }

    // 후위 순회
    public static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value);
    }

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p1991/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Character, Node> nodeMap = new HashMap<>();
        for (int i = 65; i < 65 + N; i++) {
            char val = (char) i;
            nodeMap.put(val, new Node(val));
        }
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parentVal = st.nextToken().charAt(0);
            Node parent = nodeMap.get(parentVal);

            char leftVal = st.nextToken().charAt(0);
            if (leftVal != '.') {
                Node left = nodeMap.get(leftVal);
                parent.left = left;
            }
            char rightVal = st.nextToken().charAt(0);
            if (rightVal != '.') {
                Node right = nodeMap.get(rightVal);
                parent.right = right;
            }
        }

        preorder(nodeMap.get('A'));
        System.out.println();
        inorder(nodeMap.get('A'));
        System.out.println();
        postorder(nodeMap.get('A'));
        System.out.println();
    }
}
