import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BinarySearchTreeTest {

    public static class BinarySearchTree {
        private TreeNode root;

        public class TreeNode {
            int value;
            TreeNode left, right;

            public TreeNode(int value) {
                this.value = value;
                left = right = null;
            }
        }

        public TreeNode getRoot() {
            return root;
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

        public String toJson() {
            if (root == null) return "{}";

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(this.toJsonRec(root));
            } catch (Exception e) {
                e.printStackTrace();
                return "{}";
            }
        }

        public Map<String, Object> toJsonObject() {
            return toJsonRec(root);
        }

        public Map<String, Object> toJsonRec(TreeNode node) {
            if (node == null) return null;

            Map<String, Object> map = new LinkedHashMap<>();
            map.put("value", node.value);
            map.put("left", toJsonRec(node.left));
            map.put("right", toJsonRec(node.right));
            return map;
        }
    }

    @Test
    public void testInsert() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(1);
        bst.insert(7);

        BinarySearchTree.TreeNode root = bst.getRoot();

        assertNotNull(root);
        assertEquals(3, root.value);
        assertEquals(1, root.left.value);
        assertEquals(7, root.right.value);
    }

    @Test
    public void testInOrderTraversal() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(1);
        bst.insert(7);
        bst.insert(8);
        bst.insert(5);

        List<Integer> inOrder = bst.inorderTraversal();

        assertEquals(5, inOrder.size());
        assertEquals(1, inOrder.get(0));
        assertEquals(3, inOrder.get(1));
        assertEquals(5, inOrder.get(2));
        assertEquals(7, inOrder.get(5));
        assertEquals(8, inOrder.get(4));
    }

    @Test
    public void testEmptyTree() {
        BinarySearchTree bst = new BinarySearchTree();

        BinarySearchTree.TreeNode root = bst.getRoot();

        assertNull(root);
    }

    @Test
    public void testToJson() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(1);
        bst.insert(7);
        bst.insert(8);
        bst.insert(24);

        String json = bst.toJson();

        assertNotNull(json);
        assertTrue(json.contains("\"value\":3"));
        assertTrue(json.contains("\"left\":"));
        assertTrue(json.contains("\"right\":"));
    }

    @Test
    public void testBalancedTree() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);

        BinarySearchTree.TreeNode root = bst.getRoot();

        assertNotNull(root);
        assertEquals(5, root.value);
        assertEquals(3, root.left.value);
        assertEquals(7, root.right.value);
        assertEquals(2, root.left.left.value);
        assertEquals(4, root.left.right.value);
        assertEquals(6, root.right.left.value);
        assertEquals(8, root.right.right.value);
    }
}
