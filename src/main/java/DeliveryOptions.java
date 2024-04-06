import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class DeliveryOptions {
    private Map<String, List<String>> productDeliveryOptions;

    @JsonCreator
    public DeliveryOptions(@JsonProperty("productDeliveryOptions") Map<String, List<String>> productDeliveryOptions) {
        this.productDeliveryOptions = productDeliveryOptions;
    }

    public Map<String, List<String>> getProductDeliveryOptions() {
        return productDeliveryOptions;
    }

    public void setProductDeliveryOptions(Map<String, List<String>> productDeliveryOptions) {
        this.productDeliveryOptions = productDeliveryOptions;
    }
}