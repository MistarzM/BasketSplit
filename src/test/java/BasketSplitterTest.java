import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class BasketSplitterTest {

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