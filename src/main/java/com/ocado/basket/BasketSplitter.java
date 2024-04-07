package com.ocado.basket;

import com.fasterxml.jackson.databind.ObjectMapper; // Provides functionality for converting between Java objects and matching JSON constructs
import com.fasterxml.jackson.core.type.TypeReference; // TypeReference is used to pass full generics type information, and avoid problems with type erasure
import java.nio.file.Paths; // Provides more flexible file handling
import java.io.IOException; // Required to manage errors that might occur when working with files and processing data
import java.util.List;  // import - ordered collection
import java.util.Map; // import - mapping between a set of keys and values
import java.util.ArrayList; // import - provides a resizable-array implementation of the List interface
import java.util.HashMap;   // import - provides the hash table based implementation of Map interface
import java.util.Set;   // import -  collection that contains no duplicate elements
import java.util.HashSet;   // import  - provides a hash table based implementation of the Set interface
import java.util.Iterator;  // import    - provides an iterator over a collection

public class BasketSplitter {

    private final Map<String, List<String>> deliveryOptions;
    private List<String> items;

    // A constructor responsible for parsing the configuration file and setting up the deliveryOptions map
    public BasketSplitter(String absolutePathToConfigFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.deliveryOptions = objectMapper.readValue(Paths.get(absolutePathToConfigFile).toFile(), new TypeReference<Map<String, List<String>>>() {});
    }

    // A method designed to partition the items within the basket into distinct delivery groups.
    public Map<String, List<String>> split(String absolutePathToBasketFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Read items from the basket*.json files
        List<String> items = objectMapper.readValue(Paths.get(absolutePathToBasketFile).toFile(), new TypeReference<List<String>>() {});
        Map<String, List<String>> productDeliveryOptions = this.deliveryOptions;

        Map<String, List<String>> deliveryGroups = new HashMap<>();
        Set<String> unassignedItems = new HashSet<>(items);

        // while-loop that is executed until all items are assigned to the delivery group
        while (!unassignedItems.isEmpty()) {
            Map<String, Integer> methodCoverage = new HashMap<>();

            //calculate the coverage for delivery methods
            for (String item : unassignedItems) {
                List<String> methods = productDeliveryOptions.get(item);
                for (String method : methods) {
                    methodCoverage.put(method, methodCoverage.getOrDefault(method, 0) + 1);
                }
            }

            // sorting delivery methods - by coverage(reversed)
            List<Map.Entry<String, Integer>> sortedMethods = new ArrayList<>(methodCoverage.entrySet());
            sortedMethods.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            boolean allItemsAssigned = false;

            // checks whether we can use only one delivery method
            for (Map.Entry<String, Integer> entry : sortedMethods) {
                String method = entry.getKey();
                if (entry.getValue() == unassignedItems.size()) {
                    deliveryGroups.put(method, new ArrayList<>(unassignedItems));
                    unassignedItems.clear();
                    allItemsAssigned = true;
                    break;
                }
            }

            // if not -> assign as many items as possible to the method with the highest coverage
            if (!allItemsAssigned) {
                String bestMethod = sortedMethods.get(0).getKey();
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
        }

        return deliveryGroups;
    }
}