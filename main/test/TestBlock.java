import org.junit.jupiter.api.BeforeAll;
import utils.Block;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class TestBlock {

    static Block bloc1;
    @BeforeAll
    static void initAll() {
        bloc1 = new Block(1);
    }

    @Test
    void testDeepCopy1() {
        bloc1.setProofWork("test");
        Block bloc1DeepCopy = bloc1.clone();
        bloc1DeepCopy.setProofWork("another test");
        assertEquals(bloc1.getProofWork(),"test");
    }

    @Test
    void testDeepCopy2() {
        bloc1.setProofWork("test");
        Block bloc1DeepCopy = bloc1.clone();
        bloc1DeepCopy.setProofWork("another test");
        assertNotEquals(bloc1.getProofWork(),bloc1DeepCopy.getProofWork());
    }

}