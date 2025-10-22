# 🐳 Docker Local Testing Guide

This guide helps you test your entire application stack locally before deploying to Render.

## 🎯 What This Tests

- ✅ **PostgreSQL Database** (same as Render will use)
- ✅ **inventory-service** (Backend API)
- ✅ **web-service** (Frontend UI)
- ✅ **Service Communication** (Frontend → Backend → Database)
- ✅ **Docker Build Process** (exactly as Render will do)

---

## 🚀 Quick Start (3 Commands)

### 1. Build and Start All Services
```powershell
docker-compose up --build
```

This will:
- Build both Docker images (~5-7 minutes first time)
- Start PostgreSQL database
- Start backend API (wait for DB to be ready)
- Start frontend UI (wait for backend to be ready)

### 2. Access Your Application
Once you see `Started InventoryServiceApplication` and `Started WebServiceApplication`:

- 🌐 **Frontend**: http://localhost:8080
- 🔌 **Backend API**: http://localhost:8081/items
- 🗄️ **Database**: localhost:5432 (PostgreSQL)

### 3. Test Features
- Create new items
- View all items
- Edit items
- Delete items
- Check statistics dashboard

---

## 🛑 Stop Everything

```powershell
# Stop containers (keeps data)
docker-compose down

# Stop + delete everything (clean slate)
docker-compose down -v
```

---

## 📋 Detailed Commands

### Build Without Starting
```powershell
# Build both images
docker-compose build

# Build specific service
docker-compose build inventory-service
docker-compose build web-service
```

### Start in Detached Mode (Background)
```powershell
# Start all services in background
docker-compose up -d

# View logs
docker-compose logs -f

# View logs for specific service
docker-compose logs -f inventory-service
docker-compose logs -f web-service
```

### Restart Services
```powershell
# Restart specific service
docker-compose restart inventory-service

# Rebuild and restart
docker-compose up --build inventory-service
```

### Check Service Status
```powershell
# List running containers
docker-compose ps

# View resource usage
docker stats
```

---

## 🔍 Debugging

### View Real-time Logs
```powershell
# All services
docker-compose logs -f

# Just backend
docker-compose logs -f inventory-service

# Just frontend
docker-compose logs -f web-service

# Just database
docker-compose logs -f postgres
```

### Access PostgreSQL Database
```powershell
# Connect to database
docker exec -it inventory-postgres psql -U inventory_user -d inventory_dbs

# Inside psql, run:
\dt                          # List tables
SELECT * FROM items;         # View all items
\q                          # Exit
```

### Access Container Shell
```powershell
# Backend container
docker exec -it inventory-service sh

# Frontend container
docker exec -it web-service sh

# Database container
docker exec -it inventory-postgres sh
```

---

## ⚠️ Common Issues

### Issue 1: Port Already in Use
**Error:** `Bind for 0.0.0.0:8080 failed: port is already allocated`

**Solution:**
```powershell
# Stop your locally running Spring Boot apps first
# Or change ports in docker-compose.yml:
ports:
  - "8082:8080"  # Access on 8082 instead
```

### Issue 2: Build Fails - Can't Find Parent POM
**Error:** `Non-resolvable parent POM`

**Solution:** Ensure you're in the project root directory:
```powershell
cd "C:\Users\dell\OneDrive\Documents\university\sem 5\Projects\inventory-management"
docker-compose up --build
```

### Issue 3: Database Connection Refused
**Error:** `Connection refused: postgres:5432`

**Solution:** Wait 30 seconds for database to initialize:
```powershell
# Check database health
docker-compose ps

# postgres should show "healthy" status
```

### Issue 4: Out of Memory
**Error:** `java.lang.OutOfMemoryError`

**Solution:** Increase Docker memory:
1. Docker Desktop → Settings → Resources
2. Memory: 4 GB minimum (8 GB recommended)
3. Apply & Restart

---

## 🎯 Testing Checklist

Before deploying to Render, verify:

- [ ] All 3 containers start successfully
- [ ] Frontend loads at http://localhost:8080
- [ ] Can create new items
- [ ] Can view all items
- [ ] Can edit items
- [ ] Can delete items
- [ ] Statistics show correct counts
- [ ] No errors in logs (`docker-compose logs`)

---

## 🔄 Differences: Local vs Render

| Feature | Local (Docker) | Render (Production) |
|---------|----------------|---------------------|
| Database | PostgreSQL 16 | PostgreSQL 16 ✅ |
| Backend Port | 8081 | Auto-assigned |
| Frontend Port | 8080 | Auto-assigned |
| URLs | localhost | https://xxx.onrender.com |
| SSL/HTTPS | No | Yes (automatic) |
| Environment | Docker Compose | Kubernetes |
| Cold Starts | Never | After 15 min idle |

---

## 📊 Performance Testing

### Test Backend API Directly
```powershell
# Get all items
curl http://localhost:8081/items

# Create item
curl -X POST http://localhost:8081/items `
  -H "Content-Type: application/json" `
  -d '{\"name\":\"Test Item\",\"quantity\":5,\"price\":100.50}'

# Get specific item
curl http://localhost:8081/items/1
```

### Load Testing (Optional)
```powershell
# Install Apache Bench (if needed)
# Or use Postman collection

# Simple load test
ab -n 100 -c 10 http://localhost:8080/
```

---

## 🧹 Cleanup

### Remove Everything
```powershell
# Stop and remove containers, networks, volumes
docker-compose down -v

# Remove built images
docker rmi inventory-service:latest
docker rmi web-service:latest

# Clean up all unused Docker resources
docker system prune -a --volumes
```

---

## ✅ Next Steps After Testing

Once everything works locally:

1. **Commit Docker Compose file:**
   ```powershell
   git add docker-compose.yml
   git commit -m "Add Docker Compose for local testing"
   git push origin main
   ```

2. **Deploy to Render:**
   - Go to render.com
   - Create Blueprint from your GitHub repo
   - Render will use the same Dockerfiles!

3. **Monitor Deployment:**
   - Check Render dashboard logs
   - Same logs as you saw locally!

---

## 💡 Pro Tips

1. **Fast Rebuilds:** Change code → `docker-compose up --build` rebuilds only changed services
2. **Keep Running:** Leave containers running while coding for quick tests
3. **Fresh Start:** `docker-compose down -v` gives you clean database
4. **Production Parity:** This setup mimics Render exactly!

---

**Happy Testing! 🚀** If everything works locally, it will work on Render!
