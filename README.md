# 📦 Inventory Management System

A modern, microservices-based inventory management application built with Spring Boot, featuring a clean UI with custom mint-green color scheme.

## 🌟 Features

- ✅ **Complete CRUD Operations** - Create, Read, Update, Delete items
- 📊 **Real-time Statistics** - Total items, quantity tracking, inventory value
- 🎨 **Custom UI Design** - Mint green, sage blue, dark navy color palette
- 🔐 **Environment-based Configuration** - Secure credential management
- 🐳 **Docker Ready** - Containerized deployment
- ☁️ **Cloud Deployed** - Running on Render.com (FREE)

## 🏗️ Architecture

**Microservices Architecture:**
- **inventory-service** (Port 8081) - REST API for item management
- **web-service** (Port 8080) - Thymeleaf-based frontend UI

**Tech Stack:**
- Java 17
- Spring Boot 3.5.6
- PostgreSQL 16 (Production) / MySQL 8.0 (Local)
- Thymeleaf + Bootstrap 5.3.3
- Maven Multi-Module Project
- Docker

## 🚀 Live Demo

🔗 **[View Live Application](https://web-service-xxxx.onrender.com)** _(Replace with your Render URL)_

> ⚠️ **Note:** First load may take 30-60 seconds (free tier cold start). After that, it's instant!

## 📸 Screenshots

### Main Dashboard
![Dashboard with Statistics](screenshots/dashboard.png)
- Total items count
- Total quantity tracking
- Inventory value calculation
- Sortable item table

### Create/Edit Item
![Create Item Form](screenshots/create-item.png)
- Clean form design
- Validation support
- Responsive layout

## 🛠️ Local Development Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Git

### Step 1: Clone Repository
```bash
git clone https://github.com/somya3819/inventoryManagement.git
cd inventoryManagement
```

### Step 2: Setup Database
```sql
CREATE DATABASE inventory_dbs;
```

### Step 3: Configure Environment Variables
Create `.env` file in project root:
```env
DB_URL=jdbc:mysql://localhost:3306/inventory_dbs?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=your_password_here
INVENTORY_SERVICE_PORT=8081
WEB_SERVICE_PORT=8080
INVENTORY_SERVICE_URL=http://localhost:8081
```

### Step 4: Run Services

**Terminal 1 - Backend:**
```bash
cd inventory-service
mvn spring-boot:run
```

**Terminal 2 - Frontend:**
```bash
cd web-service
mvn spring-boot:run
```

### Step 5: Access Application
- 🌐 **Web UI**: http://localhost:8080
- 🔌 **API**: http://localhost:8081

## 🐳 Docker Deployment

### Build Images
```bash
# Backend
docker build -f inventory-service/Dockerfile -t inventory-service:latest .

# Frontend
docker build -f web-service/Dockerfile -t web-service:latest .
```

### Run with Docker Compose
```bash
docker-compose up -d
```

## ☁️ Deploy to Render.com

**See detailed guide:** [RENDER_DEPLOYMENT.md](RENDER_DEPLOYMENT.md)

**Quick Steps:**
1. Push code to GitHub
2. Create Render account (free)
3. New Blueprint → Connect repository
4. Wait 10-15 minutes
5. Done! 🎉

**Cost:** ₹0 (100% FREE forever)

## 📁 Project Structure

```
inventory-management/
├── inventory-service/          # Backend REST API
│   ├── src/main/java/
│   │   └── com/inventorymanagement/inventory-service/
│   │       ├── controller/     # REST endpoints
│   │       ├── models/         # JPA entities
│   │       ├── repository/     # Data access
│   │       └── service/        # Business logic
│   ├── Dockerfile
│   └── pom.xml
├── web-service/                # Frontend UI
│   ├── src/main/
│   │   ├── java/              # Controllers
│   │   └── resources/
│   │       └── templates/      # Thymeleaf views
│   ├── Dockerfile
│   └── pom.xml
├── render.yaml                 # Render deployment config
├── .env.example               # Environment template
└── pom.xml                    # Parent POM
```

## 🎨 Color Palette

| Color | Hex | Usage |
|-------|-----|-------|
| Mint Green | `#B8D8BA` | Primary cards, accents |
| Sage Blue | `#A6B6B4` | Secondary elements |
| Dark Navy | `#1A1D23` | Headers, text |
| Golden Yellow | `#C9B963` | Highlights, borders |

## 🔧 API Endpoints

### Base URL: `http://localhost:8081`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/items` | Get all items |
| GET | `/items/{id}` | Get item by ID |
| POST | `/items` | Create new item |
| PUT | `/items/{id}` | Update item |
| DELETE | `/items/{id}` | Delete item |

### Example Request
```bash
curl -X POST http://localhost:8081/items \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop","quantity":10,"price":50000.00}'
```

## 📊 Database Schema

### Items Table
```sql
CREATE TABLE items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run specific service tests
cd inventory-service && mvn test
cd web-service && mvn test
```

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Somya**
- GitHub: [@somya3819](https://github.com/somya3819)
- University: [Your University Name]
- Semester: 5

## 🙏 Acknowledgments

- Spring Boot Team for excellent documentation
- Render.com for free hosting
- Bootstrap for UI components

## 📞 Support

For issues, questions, or suggestions:
- 🐛 Open an [issue](https://github.com/somya3819/inventoryManagement/issues)
- 💬 Start a [discussion](https://github.com/somya3819/inventoryManagement/discussions)

---

**⭐ Star this repo if you find it helpful!**

Made with ❤️ for university project | Semester 5 | 2025
