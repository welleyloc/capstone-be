### Capstone dashboard back-end

This is part 1 of my final project created for educational purposes only.

Project completion ~9-10 days for both Spring back-end and Angular front-end.

#### Services used
* Sprint Boot, Batch, Data JPA, MVC, REST
* H2-console for development
* Maven
* Postman
* mySQL

(Maybe for later: Spring Security)

#### Work flow 
(Full commit history under src folder)

1. Set up Spring Batch base code with reader/writer to create H2 database/console from main spreadsheet and a REST controller.

2. Add Spring Batch processors to transform data in table (Hashmaps, discount calculation, fix column errors, availability boolean, etc. )

3. Add service and DAL layers for CRUD operations. Add JPA annotations and @GetMapping for client access.

4. Test each CRUD operation via Postman HTTP requests and unit testing.

5. Create and test custom CRUD (@Param) for filtering by category and category+availability.

5. Add dependencies/properties to migrate from H2 database to mySQL.