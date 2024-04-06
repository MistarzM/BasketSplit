import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class BasketSplitterTest {

    @Test
    void testSplitBasket1() throws Exception {
        BasketSplitter basketSplitter = new BasketSplitter("C:/Users/micha/OneDrive/Pulpit/BasketSpliter/config.json", "C:/Users/micha/OneDrive/Pulpit/BasketSpliter/basket-1.json");
        Map<String, List<String>> result = basketSplitter.split();
        System.out.println(result);
        assertNotNull(result);
        // Add more assertions here to validate the splitting logic
    }

    @Test
    void testSplitBasket2() throws Exception {
        BasketSplitter basketSplitter = new BasketSplitter("C:/Users/micha/OneDrive/Pulpit/BasketSpliter/config.json", "C:/Users/micha/OneDrive/Pulpit/BasketSpliter/basket-2.json");
        Map<String, List<String>> result = basketSplitter.split();
        System.out.println(result);
        assertNotNull(result);
        // Similar to above, add assertions to validate the result
    }
}