package com.ps.boj.p15681;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

class Node {
    int n;
    Node parent;
    List<Node> children;

    Node(int n) {
        this.n = n;
        children = new ArrayList<>();
    }
}

public class Main {
    
    static int N;
    static int R;
    static int Q;
    static Node root;
    static int[] size;
    static Node[] nodeArr;
    static HashMap<Integer, List<Integer>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        InputManager.setInput("p15681");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = new int[N + 1];
        nodeArr = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            edges.put(i, new ArrayList<>());
            nodeArr[i] = new Node(i);
        }
        root = nodeArr[R];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            edges.get(U).add(V);
            edges.get(V).add(U);
        }

        makeTree(R, -1);
        countSubtreeNodeCnt(R);

        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            System.out.println(size[q]);
        }
    }

    static void makeTree(int currN, int parent) {
        Node currNode = nodeArr[currN];
        for (int n : edges.get(currN)) {
            if (n != parent) {
                Node connectedNode = nodeArr[n];
                currNode.children.add(connectedNode);
                connectedNode.parent = currNode;
                makeTree(connectedNode.n, currNode.n);
            }
        }
    }

    static void countSubtreeNodeCnt(int curr) {
        size[curr] = 1;
        for (Node nextNode : nodeArr[curr].children) {
            countSubtreeNodeCnt(nextNode.n);
            size[curr] += size[nextNode.n];
        }
    }
}
