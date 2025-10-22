# Environment Variables Setup

## Overview
This project uses environment variables to manage configuration securely. This keeps sensitive data like database passwords out of version control.

## Quick Start

### 1. Create your `.env` file
Copy the example file and fill in your values:
```bash
cp .env.example .env
```

### 2. Update `.env` with your credentials
Edit `.env` and set your actual values:
```properties
DB_PASSWORD=your_actual_mysql_password
```

### 3. Run the application
Spring Boot will automatically load values from `.env` if you're using:
- **Spring Boot DevTools** (already included)
- **IDE Run Configuration** (IntelliJ/Eclipse/VS Code)

Or manually set environment variables:

**Windows PowerShell:**
```powershell
$env:DB_PASSWORD="your_password"
.\mvnw.cmd spring-boot:run
```

**Windows CMD:**
```cmd
set DB_PASSWORD=your_password
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
export DB_PASSWORD=your_password
./mvnw spring-boot:run
```

## Environment Variables Reference

| Variable | Description | Default Value | Required |
|----------|-------------|---------------|----------|
| `DB_URL` | MySQL database connection URL | `jdbc:mysql://localhost:3306/inventory_dbs...` | No |
| `DB_USERNAME` | Database username | `root` | No |
| `DB_PASSWORD` | Database password | `somya282021` | No |
| `INVENTORY_SERVICE_PORT` | Backend API port | `8081` | No |
| `WEB_SERVICE_PORT` | Frontend UI port | `8080` | No |
| `INVENTORY_SERVICE_URL` | Backend API base URL | `http://localhost:8081` | No |

## Security Notes

✅ **Good:**
- `.env` is in `.gitignore` (never committed)
- `.env.example` shows template (safe to commit)
- Default values work for local development

⚠️ **Important:**
- **Never commit `.env` file**
- Share `.env.example` with team, not `.env`
- Use different `.env` values for production

## Usage in Different Environments

### Development (Local)
Use `.env` file or default values - already configured!

### Production
Set environment variables in your deployment platform:
- **Docker:** Use `docker-compose.yml` env section
- **Kubernetes:** Use ConfigMaps/Secrets
- **Cloud (AWS/Azure/GCP):** Use platform environment variables
- **Heroku/Railway:** Set via dashboard or CLI

## Syntax Explanation

In `application.properties`:
```properties
spring.datasource.password=${DB_PASSWORD:somya282021}
```

This means:
- `${DB_PASSWORD}` - Use environment variable `DB_PASSWORD`
- `:somya282021` - If not set, use this default value

## Troubleshooting

**Problem:** Application can't connect to database  
**Solution:** Check your `.env` file has correct `DB_PASSWORD`

**Problem:** Environment variables not loading  
**Solution:** Restart your IDE or Spring Boot application

**Problem:** Port already in use  
**Solution:** Change `INVENTORY_SERVICE_PORT` or `WEB_SERVICE_PORT` in `.env`
