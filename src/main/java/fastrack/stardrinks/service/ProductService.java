package fastrack.stardrinks.service;

import fastrack.stardrinks.exceptions.InventoryNotFoundException;
import fastrack.stardrinks.model.CoffeeBean;
import fastrack.stardrinks.model.Drink;
import fastrack.stardrinks.model.Goodie;
import fastrack.stardrinks.model.Inventory;
import fastrack.stardrinks.model.base.Product;
import fastrack.stardrinks.model.base.ResourceType;
import fastrack.stardrinks.repository.CoffeeBeanDAO;
import fastrack.stardrinks.repository.DrinkDAO;
import fastrack.stardrinks.repository.GoodieDAO;
import fastrack.stardrinks.repository.InventoryDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ProductService {

    CoffeeBeanDAO coffeeBeanDAO;
    GoodieDAO goodieDAO;
    DrinkDAO drinkDAO;
    InventoryService inventoryService;

    @Autowired
    public ProductService(CoffeeBeanDAO coffeeBeanDAO, GoodieDAO goodieDAO, DrinkDAO drinkDAO, InventoryService inventoryService) {
        this.coffeeBeanDAO = coffeeBeanDAO;
        this.goodieDAO = goodieDAO;
        this.drinkDAO = drinkDAO;
        this.inventoryService = inventoryService;
    }

    public List<Drink> getAllDrinks() {
        return this.drinkDAO.findAll();
    }

    public List<CoffeeBean> getAllCoffeeBeans() {
        return this.coffeeBeanDAO.findAll();
    }

    public List<Goodie> getAllGoodies() {
        return this.goodieDAO.findAll();
    }

    /***
     * Single function responsible for adding new products to their appropriate DB table as well adding an inventory record for it.
     * @param name Product name
     * @param startMonth Start of availability month
     * @param endMonth End of availability month
     * @param type Product type
     */
    @Transactional
    public void addProduct(String name, LocalDate startMonth, LocalDate endMonth, ResourceType type, int stock) {
        UUID generatedUUID = null;

        switch (type) {
            case DRINKS -> {
                Drink drink = new Drink(name, startMonth, endMonth);
                Drink savedDrink = drinkDAO.save(drink);
                generatedUUID = savedDrink.getId();
            }
            case BEANS -> {
                CoffeeBean coffeeBean = new CoffeeBean(name, startMonth, endMonth);
                CoffeeBean savedCoffeeBean = coffeeBeanDAO.save(coffeeBean);
                generatedUUID = savedCoffeeBean.getId();
            }
            case GOODIES -> {
                Goodie goodie = new Goodie(name, startMonth, endMonth);
                Goodie savedGoodie = goodieDAO.save(goodie);
                generatedUUID = savedGoodie.getId();
            }
            default -> throw new IllegalArgumentException("Invalid resource type: " + type);
        }

        this.inventoryService.save(new Inventory(generatedUUID, name, 50));
    }

    @Transactional
    public void addProduct(String name, LocalDate startMonth, LocalDate endMonth, ResourceType type) {
        this.addProduct(name, startMonth, endMonth, type, 50);
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        products.addAll(this.getAllDrinks());
        products.addAll(this.getAllGoodies());
        products.addAll(this.getAllCoffeeBeans());

        return products;
    }

    public Product getProduct(UUID id, ResourceType type) {
        switch (type) {
            case DRINKS -> {
                Optional<Drink> drink = drinkDAO.findById(id);
                if (drink.isPresent()) {
                    return drink.get();
                } else {
                    throw new InventoryNotFoundException("Could not find requested drink! Provided ID: " + id, id);
                }
            }
            case BEANS -> {
                Optional<CoffeeBean> bean = coffeeBeanDAO.findById(id);

                if (bean.isPresent()) {
                    return bean.get();
                } else {
                    throw new InventoryNotFoundException("Could not find requested coffee bean! Provided ID: " + id, id);
                }
            }
            case GOODIES -> {
                Optional<Goodie> goodie = goodieDAO.findById(id);

                if (goodie.isPresent()) {
                    return goodie.get();
                } else {
                    throw new InventoryNotFoundException("Could not find requested goodie! Provided ID: " + id, id);
                }
            }
            default -> throw new IllegalArgumentException("Invalid resource type: " + type);
        }
    }

    public Map<ResourceType, List<? extends Product>> getMenu() {
        Map<ResourceType, List<? extends Product>> menu = new EnumMap<>(ResourceType.class);

        menu.put(ResourceType.DRINKS, this.getAllDrinks());
        menu.put(ResourceType.BEANS, this.getAllCoffeeBeans());
        menu.put(ResourceType.GOODIES, this.getAllGoodies());

        return menu;
    }
}
