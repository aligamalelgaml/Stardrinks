package fastrack.stardrinks.service;


import fastrack.stardrinks.model.ShopOrder;
import fastrack.stardrinks.repository.ShopOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopOrderService {
    ShopOrderDAO shopOrderDAO;

    @Autowired
    public ShopOrderService(ShopOrderDAO shopOrderDAO) {
        this.shopOrderDAO = shopOrderDAO;
    }

//    public ShopOrder add() {
//        ShopOrder new
//    }
}
