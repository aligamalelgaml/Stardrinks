# Stardrinks

## Questions: 

- Adding custom controller links to HATOES.
- BasePathAwareController vs RestController vs RepositoryRestController

## API: 

- `GET /api/menu` to display the menu (all products)
- `/api/orders`
  - `GET` to display all previous orders`
  - `GET /{orderID}` to display a specific order using ID
  - `POST` to create a new order
- `GET /api/inventories` to display current inventory
