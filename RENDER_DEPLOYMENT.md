# 🚀 Deploy to Render.com - Step by Step Guide

## ✅ Prerequisites Completed
- ✓ PostgreSQL driver added to `inventory-service/pom.xml`
- ✓ Dockerfiles created for both services
- ✓ `render.yaml` blueprint configured
- ✓ Database configuration updated to support both MySQL (local) and PostgreSQL (production)

## 📋 Deployment Steps

### Step 1: Push Code to GitHub
```bash
git add .
git commit -m "Add Render deployment configuration with PostgreSQL support"
git push origin main
```

### Step 2: Create Render Account
1. Go to [render.com](https://render.com)
2. Click **"Get Started for Free"**
3. Sign up with your GitHub account (recommended)

### Step 3: Deploy from Dashboard
1. Click **"New +"** → **"Blueprint"**
2. Connect your GitHub repository: `somya3819/inventoryManagement`
3. Select the repository
4. Render will automatically detect `render.yaml`
5. Click **"Apply"**

### Step 4: Wait for Deployment
- **Database**: Creates PostgreSQL instance (~2-3 minutes)
- **inventory-service**: Builds Docker image and deploys (~5-7 minutes)
- **web-service**: Builds Docker image and deploys (~5-7 minutes)

Total time: ~10-15 minutes for first deployment

### Step 5: Access Your App
Once deployed, Render gives you URLs like:
- **Frontend (Web UI)**: `https://web-service-xxxx.onrender.com`
- **Backend API**: `https://inventory-service-xxxx.onrender.com`

Open the web-service URL in your browser! 🎉

---

## 🔧 Alternative: Manual Deployment (If Blueprint Doesn't Work)

### Manual Step 1: Create PostgreSQL Database
1. Dashboard → **New +** → **PostgreSQL**
2. Name: `inventory-db`
3. Database: `inventory_dbs`
4. User: `inventory_user`
5. Region: **Singapore** (closest to India)
6. Plan: **Free**
7. Click **Create Database**

### Manual Step 2: Deploy Backend (inventory-service)
1. Dashboard → **New +** → **Web Service**
2. Connect repository: `somya3819/inventoryManagement`
3. Configure:
   - **Name**: `inventory-service`
   - **Region**: Singapore
   - **Branch**: main
   - **Runtime**: Docker
   - **Dockerfile Path**: `./inventory-service/Dockerfile`
   - **Docker Context**: `.` (root directory)
4. Add Environment Variables:
   - `DATABASE_URL`: Select "From Database" → inventory-db → Connection String
   - `DB_DIALECT`: `org.hibernate.dialect.PostgreSQLDialect`
   - `SHOW_SQL`: `false`
   - `JAVA_OPTS`: `-Xmx512m -Xms256m`
5. Click **Create Web Service**

### Manual Step 3: Deploy Frontend (web-service)
1. Dashboard → **New +** → **Web Service**
2. Connect repository: `somya3819/inventoryManagement`
3. Configure:
   - **Name**: `web-service`
   - **Region**: Singapore
   - **Branch**: main
   - **Runtime**: Docker
   - **Dockerfile Path**: `./web-service/Dockerfile`
   - **Docker Context**: `.` (root directory)
4. Add Environment Variables:
   - `INVENTORY_SERVICE_URL`: `https://inventory-service-xxxx.onrender.com` (copy from Step 2)
   - `JAVA_OPTS`: `-Xmx512m -Xms256m`
5. Click **Create Web Service**

---

## ⚡ Important Notes

### Free Tier Limitations
- ✅ **100% FREE forever** (no credit card needed)
- ⏱️ **Apps sleep after 15 minutes** of inactivity
- 🐌 **First request after sleep takes ~30-60 seconds** (cold start)
- 💾 **PostgreSQL database stays awake** (data never lost)
- 📊 **750 hours/month** of compute time (enough for portfolio projects)

### Cold Start Explained
- When someone visits your app after 15+ min of no activity
- Render needs to "wake up" the containers
- First page load: 30-60 seconds
- After that: Normal speed ⚡
- **Solution**: Keep a browser tab open during demos!

### Local Development
Your local setup **still uses MySQL**! No changes needed:
```bash
# Local development (as usual)
mvn spring-boot:run
# Uses: jdbc:mysql://localhost:3306/inventory_dbs
```

Production automatically uses PostgreSQL via `DATABASE_URL` environment variable.

---

## 🎯 Testing Your Deployment

### Health Check
```bash
# Backend
curl https://inventory-service-xxxx.onrender.com/

# Frontend
curl https://web-service-xxxx.onrender.com/
```

### View Logs
1. Render Dashboard → Select service
2. Click **"Logs"** tab
3. Watch real-time application logs

### Common Issues

#### Build Failed
- Check logs for errors
- Ensure both `pom.xml` files are committed
- Verify Dockerfile paths in `render.yaml`

#### Database Connection Error
- Verify `DATABASE_URL` environment variable is set
- Check database is in "Available" status
- Ensure `DB_DIALECT` is set to PostgreSQL dialect

#### 502 Bad Gateway
- Service is still starting (wait 1-2 minutes)
- Check logs for startup errors
- Verify PORT environment variable usage

---

## 📱 Sharing Your Project

Share the **web-service URL** with anyone:
```
https://web-service-xxxx.onrender.com
```

No login required! Anyone can:
- View all items
- Create new items
- Edit/delete items
- See inventory statistics

Perfect for:
- ✅ Resume/Portfolio
- ✅ College project submissions
- ✅ Showing to recruiters
- ✅ Friends and family demos

---

## 💰 Cost Comparison

| Platform | Monthly Cost | Notes |
|----------|--------------|-------|
| **Render** | ₹0 FREE | Cold starts, perfect for portfolio |
| Railway | ₹250-600 | $5 free credit, then ~$3-8/month |
| AWS | ₹800-2000 | Complex, overkill for small projects |
| Heroku | ₹600-1200 | No free tier anymore |

---

## 🎓 For Your Resume

Add this to your projects:
```
Inventory Management System
- Microservices architecture with Spring Boot
- PostgreSQL database with JPA/Hibernate
- Deployed on Render.com (Docker containers)
- RESTful API + Thymeleaf UI
- Tech: Java 17, Spring Boot 3.5, PostgreSQL, Docker, Maven
```

---

## 🔗 Useful Links

- [Render Dashboard](https://dashboard.render.com)
- [Render Docs - Spring Boot](https://render.com/docs/deploy-spring-boot)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- Your Repository: https://github.com/somya3819/inventoryManagement

---

**Ready to deploy? Push your code and create the Blueprint! 🚀**
