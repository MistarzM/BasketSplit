import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Iterator;

public class BasketSplitter {

    private Map<String, List<String>> deliveryOptions;
    private List<String> items;
    private static final Logger LOGGER = Logger.getLogger(BasketSplitter.class.getName());

    public BasketSplitter(String absolutePathToConfigFile) throws IOException {
        LOGGER.info("Loading config file from: " + absolutePathToConfigFile);
        ObjectMapper objectMapper = new ObjectMapper();
        this.deliveryOptions = objectMapper.readValue(Paths.get(absolutePathToConfigFile).toFile(), new TypeReference<Map<String, List<String>>>() {});
    }

    public Map<String, List<String>> split(String absolutePathToBasketFile) throws IOException {
        LOGGER.info("Loading basket file from: " + absolutePathToBasketFile);
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> items = objectMapper.readValue(Paths.get(absolutePathToBasketFile).toFile(), new TypeReference<List<String>>() {});
        LOGGER.info("Items: " + items);

        Map<String, List<String>> productDeliveryOptions = this.deliveryOptions;
        LOGGER.info("Delivery options: " + productDeliveryOptions);

        Map<String, List<String>> deliveryGroups = new HashMap<>();
        Set<String> unassignedItems = new HashSet<>(items);

        while (!unassignedItems.isEmpty()) {
            Map<String, Integer> methodCoverage = new HashMap<>();

            for (String item : unassignedItems) {
                List<String> methods = productDeliveryOptions.get(item);
                for (String method : methods) {
                    methodCoverage.put(method, methodCoverage.getOrDefault(method, 0) + 1);
                }
            }

            String bestMethod = Collections.max(methodCoverage.entrySet(), Map.Entry.comparingByValue()).getKey();
            List<String> groupedItems = new ArrayList<>();

            Iterator<String> itemIterator = unassignedItems.iterator();
            while (itemIterator.hasNext()) {
                String item = itemIterator.next();
                List<String> methods = productDeliveryOptions.get(item);
                if (methods.contains(bestMethod)) {
                    groupedItems.add(item);
                    itemIterator.remove();
                }
            }

            deliveryGroups.put(bestMethod, groupedItems);
        }

        return deliveryGroups;
    }
}