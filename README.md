### Capstone dashboard back-end (Spring Boot + Angular CRUD)

This is part 1 of my final project created for educational purposes only.

Project completion ~2 wks for both Spring back-end and Angular front-end.

#### Services used
* Sprint Boot, Batch, Data JPA, REST
* H2 development database
* Maven
* Postman
* Angular 8 with Bootstrap 4, Angular Materials table
* Google Charts
* Font Awesome

(Maybe for later: mySQL migration if time permits, Spring Security)

#### Current dashboard CRUD App Actions

| Entity | Sort | Get by Id / findAll | Put / Post / Delete | CascadeType
|:---|:---|:---:|:---|:---|
| **Product** | - table columns<br>- keyword filter | [x] | [x] action directly affects<br>product, category, supplier | child: PERSIST |
| **Category** | - table columns<br>- keyword filter <br> - products| [x] | [x] action directly affects<br>product and category,<br>indirectly with supplier | parent: PERSIST, REFRESH, REMOVE, MERGE |
| **Supplier** | - table columns<br>- keyword filter <br> - products | [x] | [x] action directly affects<br>product and supplier,<br>indirectly with category | parent: PERSIST, REFRESH, REMOVE, MERGE|

#### Biggest hair-pulling obstacles encountered, tackled, or deeply questioned

* Setting foreign keys and joining columns within a Spring Batch framework...with bi-directional parent-child relationships...
* The best way to design Spring Batch code for the long run to read/write tables for three entities, and 2/3 are also maps.

#### Work flow/major commits

- [x] Set up Spring Batch base code with reader/writer to create H2 database/console from spreadsheets and REST controllers.

- [x] Add Spring Batch processor to transform data in table (fix error with the price columns)

- [x] Add service and DAL layers for CRUD operations. Add JPA and get/put/post/delete annotations/actions for client access.

- [x] Test basic/custom CRUD operations via Postman HTTP requests.

- [x] Connect Sprint Boot app (@CrossOrigin) to Angular (HttpClient)

- [x] Connect and test CRUD HTTP requests with buttons/components:
   * Add new product / category / supplier (modal components)
   * Edit existing product / category / supplier (modal components)
   * Delete existing product / category / supplier (button)
   * MVP: sort products by category and / or availability (modal components)
   
- [x] Add pagination, sort, filter.

- [ ] Write test cases and validation in front and back ends.

- [ ]  Add dependencies/properties to migrate from H2 development database to mySQL, if time permits.

- [ ]  Host on Heroku with ClearDB or some other methods.

#### Remarks

Special thanks to the TTS class (extra thumbs up to Scott, Ricardo, Jonathan, Jordan, Ken, and Austin) for allowing me to pick your brains and dealing with me during my times of confusion. 
I had no idea the challenges I was getting myself into, but because of everyone I was able to give my best shot at this problem.