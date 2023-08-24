package fastrack.stardrinks;

import fastrack.stardrinks.repository.ShopOrderDAO;
import fastrack.stardrinks.model.Inventory;
import fastrack.stardrinks.model.OrderItem;
import fastrack.stardrinks.model.ShopOrder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class StardrinksApplication {

	public static void main(String[] args) { SpringApplication.run(StardrinksApplication.class, args); }

	@Bean
	public CommandLineRunner commandLineRunner(ShopOrderDAO shopOrderDAO) {
		return runner -> doing(shopOrderDAO);
	}

	private void doing(ShopOrderDAO shopOrderDAO) {
		UUID coffee1id = UUID.fromString("0612dcbe-354e-43f1-bac8-10f34bad24e2");
		UUID drink1id = UUID.fromString("31b4669c-bde9-4f43-9998-2742bff2b11c");

		OrderItem item = new OrderItem(coffee1id, 2);
		OrderItem item2 = new OrderItem(drink1id, 3);

		ShopOrder order = new ShopOrder();

		order.addItem(item);
		order.addItem(item2);

		shopOrderDAO.save(order);

		System.out.println(shopOrderDAO.findById(order.getId()));

	}

}
