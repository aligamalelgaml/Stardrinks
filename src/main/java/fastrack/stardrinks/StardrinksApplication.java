package fastrack.stardrinks;

import fastrack.stardrinks.dao.CoffeeBeanDAO;
import fastrack.stardrinks.dao.ShopOrderDAO;
import fastrack.stardrinks.entity.Inventory;
import fastrack.stardrinks.entity.OrderItem;
import fastrack.stardrinks.entity.ShopOrder;
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
		UUID coffee1id = UUID.fromString("237c6c42-3f19-418f-b1ca-1a86292a542f");
		UUID drink1id = UUID.fromString("443bb89e-e356-4af6-a609-75f33cc1c68e");

		Inventory coffee1 = new Inventory(coffee1id);
		Inventory drink1 = new Inventory(drink1id);

		OrderItem item = new OrderItem(coffee1, 2);
		OrderItem item2 = new OrderItem(drink1, 3);

		ShopOrder order = new ShopOrder();

		order.addItem(item);
		order.addItem(item2);

		shopOrderDAO.save(order);

		System.out.println(shopOrderDAO.findById(order.getId()));

	}

}
