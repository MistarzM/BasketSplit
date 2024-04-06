import java.util.List;
import java.util.Map;

public class DeliveryOptions {
    private Map<String, List<String>> deliveryOptions;

    // Constructor for the class
    public DeliveryOptions(Map<String, List<String>> deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }

    // Getter for deliveryOptions
    public Map<String, List<String>> getDeliveryOptions() {
        return deliveryOptions;
    }

    // Setter for deliveryOptions
    public void setDeliveryOptions(Map<String, List<String>> deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }
}