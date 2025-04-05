@SpringBootTest
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
}
