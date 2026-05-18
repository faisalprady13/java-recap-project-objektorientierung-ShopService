import java.util.*;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) throws NoSuchElementException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            productToOrder.ifPresentOrElse(products::add, () -> {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                throw new NoSuchElementException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            });
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        List<Order> allOrders = orderRepo.getOrders();
        return allOrders.stream().filter(order -> order.status() == status).toList();
    }

    public void updateOrder(String id, OrderStatus newStatus) {
        Order updatedOrder = orderRepo.getOrderById(id).withStatus(newStatus);
        orderRepo.addOrder(updatedOrder);
    }

}
