# 🎟️ Event Booking API

A RESTful Event Booking API built with Spring Boot that allows users to create events, book seats, and manage bookings with proper business rules, validation, pagination, and Swagger documentation.

This is a backend-only system.

---

## 🚀 Tech Stack

* Java 17+
* Spring Boot 3.5.13
* Spring Data JPA
* H2 Database (In-memory)
* Maven
* Spring Validation
* SpringDoc OpenAPI (Swagger)

---

## 📌 Features

### 🎯 Event Management

* Create events with limited seat capacity
* Retrieve all events (paginated)
* Get event details by ID
* Auto-close event when fully booked

### 🎫 Booking Management

* Book a seat for an event
* Prevent duplicate bookings per email per event
* Cancel booking and release seat
* Retrieve bookings for an event (paginated)

---

## ⚙️ Business Rules

* Event date must be in the future
* Total seats must be greater than 0
* Cannot book if event is CLOSED
* Cannot book if seats are full (`bookedSeats >= totalSeats`)
* Same email cannot book the same event twice
* Cancelling a booking frees a seat
* Event automatically closes when fully booked

---

## 📡 API Endpoints

### 🎯 Event APIs

| Method | Endpoint                   | Description                           |
| ------ | -------------------------- | ------------------------------------- |
| POST   | /events                    | Create a new event                    |
| GET    | /events                    | Get all events (paginated)            |
| GET    | /events/{id}               | Get event by ID                       |
| GET    | /events/{eventId}/bookings | Get bookings for an event (paginated) |

---

### 🎫 Booking APIs

| Method | Endpoint                   | Description    |
| ------ | -------------------------- | -------------- |
| POST   | /events/{eventId}/bookings | Book a seat    |
| DELETE | /bookings/{bookingId}      | Cancel booking |

---

## 📄 Request & Response Format

All responses follow this structure:

```json
{
  "success": true,
  "message": "Operation successful",
  "data": {},
  "timestamp": "2026-04-23T12:00:00"
}
```

---

## 🧪 Pagination

### Events

```
GET /events?page=0&size=10
```

### Bookings

```
GET /events/{eventId}/bookings?page=0&size=10
```

---

## 📘 Swagger Documentation

Once the application is running, access Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

---

## 🛠️ How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/your-username/event-booking-api.git
cd event-booking-api
```

### 2. Build the project

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn spring-boot:run
```

---

## 💾 H2 Database Console

Access H2 console:

```
http://localhost:8080/h2-console
```

### Default settings:

* JDBC URL: `jdbc:h2:mem:testdb`
* Username: `sa`
* Password: *(empty)*

---

## 📌 Assumptions

* Email uniqueness is enforced per event only
* Booking ID is used for cancellation
* Event status is either OPEN or CLOSED
* In-memory H2 database resets on restart

---

## 📈 Possible Improvements (Optional)

* JWT Authentication
* User management system
* Event categories
* Payment integration
* Email notifications
* Persistent database (PostgreSQL/MySQL)

---

## 👨‍💻 Author

**Nathan Adebesin**
Email: [nathanadebesin@gmail.com](mailto:nathanadebesin@gmail.com)
GitHub: https://github.com/omnizzy
