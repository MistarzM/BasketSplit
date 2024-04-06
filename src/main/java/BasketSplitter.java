import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class BasketSplitter {

    private DeliveryOptions deliveryOptions;
    private List<String> items;
    private static final Logger LOGGER = Logger.getLogger(BasketSplitter.class.getName());

    public BasketSplitter(String absolutePathToConfigFile, String absolutePathToBasketFile) throws IOException {
        LOGGER.info("Loading config file from: " + absolutePathToConfigFile);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<String>> deliveryOptionsMap = objectMapper.readValue(Paths.get(absolutePathToConfigFile).toFile(), new TypeReference<Map<String, List<String>>>() {});
        this.deliveryOptions = new DeliveryOptions(deliveryOptionsMap);
        this.items = objectMapper.readValue(Paths.get(absolutePathToBasketFile).toFile(), new TypeReference<List<String>>() {});
    }

    public Map<String, List<String>> split() {
        LOGGER.info("Items: " + items);
        LOGGER.info("Delivery options: " + deliveryOptions.getDeliveryOptions());
        Map<String, List<String>> deliveryGroups = new HashMap<>();

        for (String item : items) {
            List<String> methods = deliveryOptions.getDeliveryOptions().get(item);
            if (methods != null) {
                for (String method : methods) {
                    deliveryGroups.computeIfAbsent(method, k -> new ArrayList<>()).add(item);
                }
            }
        }

        return deliveryGroups;
    }
}