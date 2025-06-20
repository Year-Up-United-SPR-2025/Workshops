# About Workshop 9 Connecting your Car Dealership to a Database 💾
For this workshop, I developed a RESTful API based on a previous project where I built a car dealership system using Java and the MySQL Driver. 
The project transitioned from using CSV files to directly connecting with a MySQL database that I designed and implemented myself.

# Technology Used 🖥️
For this project, I designed and implemented a car dealership database using SQL tools such as `DBeaver` and `MySQL`. I created structured tables, populated them with initial data, and wrote SQL queries to verify data integrity and functionality. To ensure smooth deployment, I generated a testable `.sql` file that validates the database setup.

I then used `Java Spring Boot` with relevant Web and MySQL dependencies to build backend tools for interacting with the database. After launching the application, I leveraged `Postman` to test and confirm successful API calls and database connectivity.

# Roadmap 💾
I reviewed the documentation and used `start.spring.io` to create the application, selecting the `Spring Web` and `MySQL` dependencies for the project.

Packages and Classes
---
1. Went into my previous workshop and pulled the necessary classes needed for this project. The `LeaseContracts, Vehicle, and Sales` classes were required, and the `DAOs` were rewritten for better clarity and structure.
2. Created Packages in my project to separate my code in the necessary classes:
* The `Config` package contains the `DataBaseConfig` class, which loads and provides database connection properties from the Spring application configuration as a reusable Spring-managed component.
* The `Controllers` package contains:
`LeaseContractsController`. This Spring Boot REST controller exposes endpoints to create and retrieve lease contracts by delegating the business logic to a `LeaseDao` implementation.

  



