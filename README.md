### Capstone dashboard back-end (Spring Boot + Angular CRUD)

This is part 1 of my final project created for educational purposes only.

Project completion ~2 wks for both Spring back-end and Angular front-end.

#### Services used
* Sprint Boot, Batch, Data JPA, REST
* H2 development database
* Maven
* Postman
* Angular 8 with Bootstrap 4, Angular Materials (table sort/pagination)

(Maybe for later: mySQL migration if time permits, Spring Security)

#### Biggest hair-pulling back-end obstacles encountered & tackled 

* Setting foreign keys and joining columns with a Spring Batch framework...with a bi-directional parent-child relationship...
* The best way to design this Spring Batch code for the long run to read/write tables from three entities, and 2/3 are also maps.

#### Work flow/major commits

1. Set up Spring Batch base code with reader/writer to create H2 database/console from main spreadsheet and a REST controller.

2. Add Spring Batch processors to transform data in table (fix error with the price columns)

3. Add service and DAL layers for CRUD operations. Add JPA and get/put/post/delete annotations/actions for client access.

4. Test basic/custom CRUD operations via Postman HTTP requests.

5. Connect Sprint Boot app (@CrossOrigin) to Angular (HttpClient)

6. Connect and test CRUD HTTP requests with buttons/components:
   * Add new product (modal component)
   * Edit existing product (button)
   * Delete existing product (button)
   * Sort products by category/availability (modal component)
   
7. Add pagination

8. Write test cases in front and back ends for CRUD and processors.

9. Add dependencies/properties to migrate from H2 development database to mySQL, if time permits.

10. Host on Heroku with ClearDB or some other methods.

(Maybe for later: Connect server-size code to Google Charts)

#### Remarks

Thanks to the TTS class (especially Scott, Ricardo, Jonathan, Jordan, and Austin) for allowing me to pick your brains and dealing with me during my times of confusion. 
I had no idea the challenges I was getting myself into, but because of everyone I was able to give my best shot at this problem.