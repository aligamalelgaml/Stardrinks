package fastrack.stardrinks;

import fastrack.stardrinks.model.base.ResourceType;
import fastrack.stardrinks.repository.InventoryDAO;
import fastrack.stardrinks.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class StardrinksApplication {

	public static void main(String[] args) { SpringApplication.run(StardrinksApplication.class, args); }

	@Bean
	public CommandLineRunner commandLineRunner(ProductService productService) {
		return runner -> init(productService);
	}

	/**
	 * Simple function used to init the DB.
	 */
	private void init(ProductService productService) {
		// Beans
		productService.addProduct("Sumatra", LocalDate.parse("2023-04-01"), LocalDate.parse("2023-09-30"), new BigDecimal("1.4"), ResourceType.BEANS);
		productService.addProduct("Espresso", LocalDate.parse("2023-02-01"), LocalDate.parse("2023-11-30"), new BigDecimal("1.2"), ResourceType.BEANS);
		productService.addProduct("Guatemala", LocalDate.parse("2023-05-01"), LocalDate.parse("2023-08-31"), new BigDecimal("1.8"), ResourceType.BEANS);
		productService.addProduct("Colombian", LocalDate.parse("2023-03-01"), LocalDate.parse("2023-10-31"), new BigDecimal("2.4"), ResourceType.BEANS);
		productService.addProduct("Arabica", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-12-31"), new BigDecimal("3.2"), ResourceType.BEANS);
		productService.addProduct("Ethiopian", LocalDate.parse("2023-06-01"), LocalDate.parse("2023-07-31"), new BigDecimal("1.0"), ResourceType.BEANS);

		// Drinks
		productService.addProduct("Cranberry Spritzer", LocalDate.parse("2023-11-01"), LocalDate.parse("2023-12-31"), new BigDecimal("1.4"), ResourceType.DRINKS);
		productService.addProduct("Iced Ginger Lemonade", LocalDate.parse("2023-06-01"), LocalDate.parse("2023-09-30"), new BigDecimal("4.2"), ResourceType.DRINKS);
		productService.addProduct("Mango Lassi", LocalDate.parse("2023-05-01"), LocalDate.parse("2023-08-31"), new BigDecimal("4.2"), ResourceType.DRINKS);
		productService.addProduct("Sparkling Apple Cider", LocalDate.parse("2023-10-01"), LocalDate.parse("2023-12-31"), new BigDecimal("0.2"), ResourceType.DRINKS);
		productService.addProduct("Pumpkin Spice Latte", LocalDate.parse("2023-09-01"), LocalDate.parse("2023-11-30"), new BigDecimal("5.2"), ResourceType.DRINKS);
		productService.addProduct("Iced Peppermint Mocha", LocalDate.parse("2023-12-01"), LocalDate.parse("2023-02-28"), new BigDecimal("2.2"), ResourceType.DRINKS);

		// Goodies
		productService.addProduct("Peppermint Brownie", LocalDate.parse("2023-12-01"), LocalDate.parse("2023-02-28"), new BigDecimal("1.5"), ResourceType.GOODIES);
		productService.addProduct("Lemon Poppy Seed Muffin", LocalDate.parse("2023-06-01"), LocalDate.parse("2023-09-30"), new BigDecimal("3.3"), ResourceType.GOODIES);
		productService.addProduct("Cranberry Scone", LocalDate.parse("2023-11-01"), LocalDate.parse("2023-12-31"), new BigDecimal("1.7"), ResourceType.GOODIES);
		productService.addProduct("Caramel Apple Pie", LocalDate.parse("2023-10-01"), LocalDate.parse("2023-12-31"), new BigDecimal("5.2"), ResourceType.GOODIES);
		productService.addProduct("Mango Cheesecake", LocalDate.parse("2023-05-01"), LocalDate.parse("2023-08-31"), new BigDecimal("3.2"), ResourceType.GOODIES);
		productService.addProduct("Pumpkin Donut", LocalDate.parse("2023-09-01"), LocalDate.parse("2023-11-30"), new BigDecimal("6.2"), ResourceType.GOODIES);
	}

}
