### Capstone back-end (Spring Boot + Angular CRUD)

This is part 1 of my final project created for educational purposes only.

Project completion ~9-10 days for both Spring back-end and Angular front-end.

#### Services used
* Sprint Boot, Batch, Data JPA, MVC, REST
* H2-console for development
* Maven
* Postman
* mySQL
* Angular 8 with Bootstrap 4

(Maybe for later: Spring Security)

#### Work flow/general commits

1. Set up Spring Batch base code with reader/writer to create H2 database/console from main spreadsheet and a REST controller.

2. Add Spring Batch processors to transform data in table (Hashmaps, discount calculation, fix column errors, availability boolean, etc. )

3. Add service and DAL layers for CRUD operations. Add JPA and Spring MVC get/put/post/delete annotations for client access.

4. Test basic/custom CRUD operations via Postman HTTP requests.

5. Connect Sprint Boot app (@CrossOrigin) to Angular (HttpClient)

6. Connect and test CRUD HTTP requests with buttons/components:
   * Add new product (modal component)
   * Edit existing product (button)
   * Delete existing product (button)
   * Sort products by category/availability (modal component)
   
7. Add pagination

8. Write test cases in front and back ends for CRUD and processors.

9. Add dependencies/properties to migrate from H2 development database to mySQL.

10. Host on Heroku with ClearDB. 

(Maybe for later: Connect server-size code to Google Charts)