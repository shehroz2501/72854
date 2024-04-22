# 72854

Online Shopping App Report
Overview
The Online Shopping App is designed to provide users with a seamless shopping experience, incorporating essential features such as authentication, product listing, cart management, order history, and user profile management. The app leverages the Fake Store API for product data and custom-built components for user management and other functionalities.

Implementation Details
Authentication
1.1 Signup
Users can sign up by providing a username, password, name, address, phone number, and optional geolocation details (latitude and longitude).
Upon successful signup, a new user is created with the provided details.
1.2 Log In
Users can log in using their username and password.
The login credentials are verified against the stored user data.
A JWT token is generated upon successful login and stored locally to keep the user logged in across app restarts.
1.3 Persistent User Session
The app stores the userID locally after login to maintain the user's session.
The stored userID is used to fetch user details and manage user-specific functionalities.
Product Listing
2.1 Product Categories
The app fetches and displays a list of product categories from the Fake Store API.
Users can browse through different categories to explore products.
2.2 Products in Category
Upon selecting a category, the app fetches and displays the products associated with that category.
Each product is represented with its name, price, and an option to view detailed information.
2.3 Product Details
Users can view detailed product information, including description, price, and an option to add the product to the cart.
Quantity controls are provided to adjust the number of items to be added to the cart.
Cart Management
3.1 Cart Summary
The cart displays a summary of all products added by the user.
Each item shows the product name, price, quantity, and total price.
3.2 Total Amount
The total amount in the cart is calculated and displayed, rounded to two decimal places.
3.3 Purchase and Order Notification
Users can proceed to purchase by clicking the purchase button.
Upon successful purchase, an order notification is displayed to confirm the order.
Order History
4.1 List Orders
The app fetches and displays the user's order history from the Fake Store API.
Each order is listed with its order ID, date, and total amount.
4.2 Order Details
Users can view detailed information about a specific order, including the list of products ordered.
4.3 Product Details
Clicking on a product within an order redirects users to the product details page.
User Profile
5.1 User Details
The app fetches and displays user details using the stored userID.
User details include username, name, address, and phone number.
5.2 Profile Image
A random circular profile image is displayed for the user, sourced from "https://thispersondoesnotexist.com/".
5.3 Logout
Users can log out by clicking the logout button.
Upon logout, the app returns to the Signup/Log In page, and the stored session data is cleared.
UI/Implementational Requirements
6.1 Lazy Lists
Lazy lists are implemented for all list views, including categories, products, and orders, to optimize performance.
6.2 Authentication Token
An authentication token is attached to all API requests after login until logout to ensure secure communication.
6.3 About this App
A small "About this app" button is available on the profile page, which displays copyright details and credits upon clicking.
Bonus Features
7.1 ViewPager2 with Bottom TabLayout
A ViewPager2 with a bottom TabLayout is implemented to navigate between Shop, Cart, Orders, and Profile sections.
7.2 Map Fragment
A map fragment is displayed based on the GPS coordinates in the user profile, allowing users to visualize their location.
Design Decisions
Data Types: All resources (User, Product, Cart, etc.) adhere to the JSON API responses structure to facilitate automatic parsing using Gson and JSON building for POST requests.
Geolocation: GPS coordinates (latitude and longitude) are generated randomly during user signup but can be updated manually through a text box.
Conclusion
The Online Shopping App offers a comprehensive shopping experience with essential features, intuitive user interface, and efficient implementation. By leveraging the Fake Store API for product data and adhering to best practices in app development, the app ensures reliability, security, and user satisfaction.
