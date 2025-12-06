# Rideshare - Spring Boot Backend

A Spring Boot cartoon backend for stimulating cab booking application. I started building it to get started with Spring Boot framework. The project covers REST APIs, database integration, authentication, and a layered architecture similar to production applications.


## What I Learned

- Building REST APIs with Spring Boot
- Spring Security & JWT authentication  
- Spring database integration  
- Layered architecture (Controller → Service → Repository)  
- Request/Response DTOs  
- API design patterns

## Tech Stack

- **Language**: Java | **Framework**: Spring Boot | **Database**: Mongodb | **Security**: Spring Security + JWT | **Build**: Maven

## APIs

1. **POST /api/auth/register** - Register user/driver
2. **POST /api/auth/login** - Login & get JWT token
3. **POST /api/rides/request** - Create ride request
4. **GET /api/rides/available** - View available rides
5. **POST /api/rides/{id}/accept** - Accept a ride
6. **GET /api/driver/{id}** - Get driver profile
7. **GET /api/user/{id}** - Get user profile


## Project Structure

```
src/main/java/com/deepak/uber/
├── controller/      → API endpoints
├── service/         → Business logic
├── repository/      → Database queries
├── Entity/          → Database models
├── dto/             → Request/Response objects
└── config/          → Security & JWT configuration
```

## Quick Start

1. **Clone & Setup**
   ```bash
   git clone https://github.com/ChampDeepak/rideshare.git
   cd rideshare
   ```

2. **Configure Database** (edit `src/main/resources/application.yaml`)
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/rideshare
       username: root
       password: your_password
   ```

3. **Build & Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   App runs on `http://localhost:8080`

## Example API Calls

**Register:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email": "user@example.com", "password": "pass", "firstName": "John", "role": "USER"}'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "user@example.com", "password": "pass"}'
```

**Request Ride:**
```bash
curl -X POST http://localhost:8080/api/rides/request \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"pickupLocation": "123 Main St", "dropoffLocation": "456 Park Ave"}'
```



## License

MIT License - feel free to use and modify
