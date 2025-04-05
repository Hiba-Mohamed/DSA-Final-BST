package com.example.demo.BTS;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private TreeNode root;

    public class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
            left = right = null;
        }
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRec(root, result);
        return result;
    }

    private void inorderTraversalRec(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversalRec(root.left, result);
            result.add(root.value);
            inorderTraversalRec(root.right, result);
        }
    }
}
