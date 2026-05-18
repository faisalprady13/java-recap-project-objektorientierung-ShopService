import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_throwNoSuchElementException() {
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        assertThrows(NoSuchElementException.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void getOrdersByStatus_shouldReturnOrdersWithStatusProcessing_whenGivenStatusProcessing() {

        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        shopService.addOrder(List.of("1"));
        shopService.addOrder(List.of("1"));
        shopService.addOrder(List.of("1"));

        assertEquals(3, shopService.getOrdersByStatus(OrderStatus.PROCESSING).size());
        assertEquals(0, shopService.getOrdersByStatus(OrderStatus.IN_DELIVERY).size());
    }
}
