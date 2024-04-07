import com.ocado.basket.BasketSplitter; // Imports the BasketSplitter class
import org.junit.jupiter.api.Test;  //Import used to mark methods as test methods
import java.util.List;  //import - ordered collection
import java.util.Map;   //import - mapping between a set of keys and values
import static org.junit.jupiter.api.Assertions.*;   //import - used to check condition in the test methods

class BasketSplitterTest {
    //unit tests for the BasketSplitter class
    @Test
    void testSplitBasket1() throws Exception {
        BasketSplitter basketSplitter = new BasketSplitter("Zadanie/config.json");
        Map<String, List<String>> result = basketSplitter.split("Zadanie/basket-1.json");
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    void testSplitBasket2() throws Exception {
        BasketSplitter basketSplitter = new BasketSplitter("Zadanie/config.json");
        Map<String, List<String>> result = basketSplitter.split("Zadanie/basket-2.json");
        System.out.println(result);
        assertNotNull(result);
    }
}