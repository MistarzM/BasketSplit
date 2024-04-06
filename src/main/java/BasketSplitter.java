import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.*;

public class BasketSplitter {

    private DeliveryOptions deliveryOptions;

    public BasketSplitter(String absolutePathToConfigFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.deliveryOptions = objectMapper.readValue(Paths.get(absolutePathToConfigFile).toFile(), new TypeReference<DeliveryOptions>() {});
    }

    public Map<String, List<String>> split(List<String> items) {
        // Resultant map to hold the delivery method and the items that can be shipped by it
        Map<String, List<String>> deliveryGroups = new HashMap<>();

        // Temporary map to hold each item's delivery methods
        Map<String, Set<String>> itemDeliveryMethods = new HashMap<>();

        // Populate the temporary map with delivery methods for each item
        for (String item : items) {
            Map<String, List<String>> productDeliveryOptions = deliveryOptions.getProductDeliveryOptions();
            List<String> methods = productDeliveryOptions.get(item);
            if (methods != null) {
                itemDeliveryMethods.put(item, new HashSet<>(methods));
            }
        }

        // Find common delivery methods for all items and group items accordingly
        while (!itemDeliveryMethods.isEmpty()) {
            Set<String> commonMethods = new HashSet<>(itemDeliveryMethods.values().iterator().next());

            for (Set<String> methods : itemDeliveryMethods.values()) {
                commonMethods.retainAll(methods);  // Retain only the common methods
            }

            if (!commonMethods.isEmpty()) {
                String selectedMethod = commonMethods.iterator().next();  // Select one common method
                List<String> groupedItems = new ArrayList<>();

                // Group items for the selected delivery method
                Iterator<Map.Entry<String, Set<String>>> it = itemDeliveryMethods.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Set<String>> entry = it.next();
                    if (entry.getValue().contains(selectedMethod)) {
                        groupedItems.add(entry.getKey());
                        it.remove();  // Remove item once it has been grouped
                    }
                }

                deliveryGroups.put(selectedMethod, groupedItems);
            }
        }

        return deliveryGroups;
    }
}
