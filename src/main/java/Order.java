import lombok.With;

import java.time.Instant;
import java.util.List;

@With
public record Order(
        String id,
        List<Product> products,
        OrderStatus status,
        Instant orderDate
) {

    public Order(
            String id,
            List<Product> products,
            OrderStatus status) {
        this(id, products, status, Instant.now());
    }
}
