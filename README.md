# Mart - E-commerce Application

Mart is a comprehensive E-commerce application developed with Angular (front-end) and Spring Boot (back-end). This project serves as an exemplary E-commerce platform, enabling users to explore products, manage their shopping carts, and securely complete purchases.

<img src="https://github.com/Samfrosh/portfolio/blob/main/src/assets/images/mart.png" alt="">

**Live Demo**: [Mart Website](https://mart-s.netlify.app/)
- **Login**: samuel@gmail.com
- **Password**: samuel

## Table of Contents

- [Project Overview](#project-overview)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Key Features](#key-features)
- [Installation](#installation)
- [Contributing](#contributing)
- [Screenshots](#screenshots)

## Project Overview

Mart combines Angular for the front-end and Spring Boot for the back-end to deliver a robust E-commerce platform. With features such as user registration and login, product browsing, cart management, secure payment processing, and JWT-based security, this project provides a complete shopping experience.

## Project Structure

The project is organized into two primary folders within the repository:

1. **Angular**: Contains the Angular front-end code for the project.
2. **Spring Boot**: Contains the Spring Boot back-end code for the project.

## Technologies Used

- **Back-end**: Utilizes Spring Boot, Spring Data, and Spring Security for seamless backend operations. JPA/Hibernate is used for Object-Relational Mapping, while MySQL stores data. JUnit is employed for testing.
- **Front-end**: Developed using TypeScript and Angular, enhanced with Tailwind CSS and SCSS for a visually appealing user interface.
- **Security**: Implements JWT (JSON Web Token) and Spring Security to protect user data.
- **API**: Facilitates communication between the front-end and back-end through RESTful APIs.
- **Build Tools**: The project is built using Maven (for the server) and npm/yarn (for the client).

## Key Features

Mart offers a range of essential E-commerce features, including:

- **User Registration and Login**: Seamless user onboarding and login process.
- **Product Browsing and Searching**: Easily find and filter products.
- **Shopping Cart Management**: Add, remove, and manage items in your cart.
- **Secure Payment Processing**: Safely complete transactions with a secure payment gateway.
- **JWT-based Security**: Protects user data through JSON Web Tokens and Spring Security.

## Installation

To set up and run the project, follow these steps:

1. **Clone the repository**:

   ```bash
   git clone https://github.com/Samfrosh/Mart-Ecommerce-Application.git
   Navigate to the Angular folder:

bash
Copy code
cd mart-stores
Install the Angular CLI:

bash
Copy code
npm install -g @angular/cli
Install project dependencies:

bash
Copy code
npm install
Start the Angular development server:

bash
Copy code
ng serve
Open a new terminal window/tab and navigate to the Spring Boot folder:

bash
Copy code
cd Backend/Mart
Install project dependencies:

bash
Copy code
./mvnw install
Start the Spring Boot server:

bash
Copy code
./mvnw spring-boot:run
Open a web browser and navigate to http://localhost:4200 to view the application.

Note: Node.js and Java need to be installed on your machine to run this project.

Contributing
Contributions to the Mart project are highly encouraged! If you discover a bug or have ideas for new features, please open an issue on the project's GitHub repository.

Screenshots
Explore the Mart E-commerce Application:

Register
Login
Product View
Search
Product Details
Cart
Thank you for considering contributing to this project! Your support and contributions are greatly appreciated.
