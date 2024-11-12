Andrés León



## How deploy
just run the following command:
```
docker-compose up --build
```  

## Considerations
- The code have been tested in a AWS environment with a Lightsail MySql Instance. 

## Endpoints
- Add New Franchise: 
```
localhost:3306/api/franchises
```
- Add Branch to franchise: 
```
localhost:3306/api/franchises/{franchiseId}/branches
```
- Add product to branch: 
```
localhost:3306/api/branches/{branchId}/products
```
- Remove product from branch: 
```
localhost:3306/api/branches/{branchId}/products/{productId}
```
- Top stock products per franchise:
```
localhost:3306/api/franchises/{franchiseId}/top-stock-products
```
- Modify stock product: 
```
localhost:3306/api/products/{productId}/stock
```

*Note:*
- In the project you will find an endpoint file to test in postman.

*File postman name:*
```
Franchise technical test.postman_collection
```