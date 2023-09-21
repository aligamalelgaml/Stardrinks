package fastrack.stardrinks.services;

import fastrack.stardrinks.model.CoffeeBean;
import fastrack.stardrinks.model.Drink;
import fastrack.stardrinks.model.Inventory;
import fastrack.stardrinks.model.base.Product;
import fastrack.stardrinks.model.base.ResourceType;
import fastrack.stardrinks.repository.CoffeeBeanDAO;
import fastrack.stardrinks.repository.DrinkDAO;
import fastrack.stardrinks.repository.GoodieDAO;
import fastrack.stardrinks.repository.ProductDAO;
import fastrack.stardrinks.service.InventoryService;
import fastrack.stardrinks.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    CoffeeBeanDAO coffeeBeanDAO;
    @Mock
    GoodieDAO goodieDAO;
    @Mock
    DrinkDAO drinkDAO;
    @Mock
    InventoryService inventoryService;
    @Mock
    ProductDAO productDAO;

    @Test
    @DisplayName("Test getAllDrinks")
    public void testGetAllDrinks() {
        // Arrange
        List<Drink> drinks = List.of(new Drink("Test Drink 1", LocalDate.now(), LocalDate.now(), BigDecimal.ONE), new Drink("Test Drink 2", LocalDate.now(), LocalDate.now(), BigDecimal.TEN));
        when(drinkDAO.findAll()).thenReturn(drinks);

        // Act
        List<Drink> result = productService.getAllDrinks();

        // Assert
        assertEquals(drinks.size(), result.size());
        verify(drinkDAO, times(1)).findAll();
    }

    @Test
    @DisplayName("Test addProduct with success")
    public void testAddProduct_Success() {
        // Arrange
        String productName = "Coffee";
        LocalDate startMonth = LocalDate.now();
        LocalDate endMonth = LocalDate.now().plusMonths(1);
        BigDecimal price = BigDecimal.valueOf(5.99);
        ResourceType type = ResourceType.BEANS;


        CoffeeBean coffeeBean = new CoffeeBean(productName, startMonth, endMonth, price);
        when(coffeeBeanDAO.save(any(CoffeeBean.class))).thenReturn(coffeeBean);

        // Act
        Product result = productService.addProduct(productName, startMonth, endMonth, price, type, 50);

        // Assert
        assertNotNull(result);
        assertEquals(type, result.getType());

        verify(productDAO, times(1)).findByName(productName);
        verify(coffeeBeanDAO, times(1)).save(any(CoffeeBean.class));
        verify(inventoryService, times(1)).save(any(Inventory.class));
    }

}
