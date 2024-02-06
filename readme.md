# Painting E-commerce Store RESTful API

This repository contains the backend implementation of a Painting E-commerce Store, providing a RESTful API developed with Java Spring Boot, connected to a MySQL database, and documented using Swagger.

## Features

- **Product Management:** Perform CRUD operations for paintings, including creation, retrieval, updating, and deletion.

- **Order Processing:** Handle customer orders, track order status, and manage the order fulfillment process.

<!-- - **User Authentication:** Ensure secure user authentication and authorization mechanisms to maintain data integrity. -->

- **Swagger Documentation:** Explore and test API endpoints easily with interactive documentation generated by Swagger.

## Technologies Used

- **Java Spring Boot:** A powerful and flexible Java-based framework for building scalable and reliable applications.

- **MySQL Database:** A robust relational database for storing product, order, and user information.

- **Swagger:** Simplifies API documentation, making it easy for developers to understand and utilize the API.

## How to Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/painting-ecommerce-api.git
   ```

2. **Configure MySQL Database:**

   - Open `src/main/resources/application.properties`
   - Update the database connection settings.

3. **Run the Spring Boot Application:**

   - Execute the main class: `PaintingApplication.java`

4. **Access Swagger Documentation:**
   - Open your browser and go to: `http://localhost:8080/swagger`

## Contributing

We welcome contributions! Feel free to report issues, suggest improvements, or submit pull requests to enhance the functionality and features of the API.

## License

This project is licensed under the [MIT License](LICENSE).