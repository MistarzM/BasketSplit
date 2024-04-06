import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class BasketSplitterTest {
    private BasketSplitter basketSplitter;

    @BeforeEach
    void setUp() throws Exception {
        // Assuming the config file is loaded in the BasketSplitter constructor
        basketSplitter = new BasketSplitter("C:/Users/micha/OneDrive/Pulpit/BasketSpliter/config.json");
    }

    @Test
    void testSplitBasket1() throws Exception {
        List<String> basket1 = Files.readAllLines(Paths.get("C:/Users/micha/OneDrive/Pulpit/BasketSpliter/basket-1.json"));
        Map<String, List<String>> result = basketSplitter.split(basket1);
        assertNotNull(result);
        // Add more assertions here to validate the splitting logic,
        // for example, check if the number of groups is as expected,
        // if the items are correctly distributed, etc.
    }

    @Test
    void testSplitBasket2() throws Exception {
        List<String> basket2 = Files.readAllLines(Paths.get("C:/Users/micha/OneDrive/Pulpit/BasketSpliter/basket-2.json"));
        Map<String, List<String>> result = basketSplitter.split(basket2);
        assertNotNull(result);
        // Similar to above, add assertions to validate the result
    }
}
