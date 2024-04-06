import java.util.List;
import java.util.Map;

public class DeliveryOptions {
    private Map<String, List<String>> deliveryOptions;

    public DeliveryOptions(Map<String, List<String>> deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }

    public Map<String, List<String>> getDeliveryOptions() {
        return deliveryOptions;
    }

    public void setDeliveryOptions(Map<String, List<String>> deliveryOptions) {
        this.deliveryOptions = deliveryOptions;
    }
}