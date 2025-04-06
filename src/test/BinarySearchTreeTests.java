@Test
public class BinarySearchTreeTests {
    private BinarySearchTree bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree();
    }

    @Test
    public void testInsertAndInorderTraversal() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);
        List<Integer> expected = Arrays.asList(20, 30, 40, 50, 60, 70, 80);
        assertEquals(expected, bst.inorderTraversal());
    }

    @Test
    public void testSearchFound() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertTrue(bst.search(5));
        assertTrue(bst.search(10));
        assertTrue(bst.search(15));
    }

    @Test
    public void testSearchNotFound() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        assertFalse(bst.search(20));
        assertFalse(bst.search(0));
    }

    @Test
    public void testInorderTraversalEmptyTree() {
        List<Integer> expected = List.of();
        assertEquals(expected, bst.inorderTraversal());
    }

    @Test
    public void testInsertDuplicateValue() {
        bst.insert(10);
        bst.insert(10);
        List<Integer> expected = List.of(10);
        assertEquals(expected, bst.inorderTraversal());
    }
}
