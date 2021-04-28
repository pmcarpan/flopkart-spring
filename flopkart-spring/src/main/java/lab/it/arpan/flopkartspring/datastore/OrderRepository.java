package lab.it.arpan.flopkartspring.datastore;

import java.util.List;

import lab.it.arpan.flopkartspring.model.User;
import lab.it.arpan.flopkartspring.model.request.OrderCreateRequest;

public interface OrderRepository {
    
    boolean createOrder(OrderCreateRequest orderCreateRequest, User user);

    boolean createOrderItems(Long orderId, List<Long> productIds);

}
