# Healthcare Appointment App - Endpoint Testing Script
Write-Host "=== Healthcare Appointment App - Endpoint Testing ===" -ForegroundColor Green

# Test 1: Health Check
Write-Host "`n1. Testing Health Check..." -ForegroundColor Yellow
try {
    $health = Invoke-WebRequest -Uri "http://localhost:8080/actuator/health" -UseBasicParsing
    Write-Host "✅ Health Check: SUCCESS" -ForegroundColor Green
    Write-Host "Response: $($health.Content)" -ForegroundColor Gray
} catch {
    Write-Host "❌ Health Check: FAILED" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 2: User Registration
Write-Host "`n2. Testing User Registration..." -ForegroundColor Yellow
$registerData = @{
    username = "testuser"
    email = "test@example.com"
    password = "password123"
    phone = "1234567890"
    role = "PATIENT"
} | ConvertTo-Json

try {
    $register = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/register" -Method POST -Body $registerData -ContentType "application/json" -UseBasicParsing
    Write-Host "✅ User Registration: SUCCESS" -ForegroundColor Green
    Write-Host "Response: $($register.Content)" -ForegroundColor Gray
} catch {
    Write-Host "❌ User Registration: FAILED" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 3: User Login
Write-Host "`n3. Testing User Login..." -ForegroundColor Yellow
$loginData = @{
    username = "testuser"
    password = "password123"
} | ConvertTo-Json

try {
    $login = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/login" -Method POST -Body $loginData -ContentType "application/json" -UseBasicParsing
    Write-Host "✅ User Login: SUCCESS" -ForegroundColor Green
    $token = ($login.Content | ConvertFrom-Json).token
    Write-Host "Token received: $($token.Substring(0,20))..." -ForegroundColor Gray
} catch {
    Write-Host "❌ User Login: FAILED" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
    $token = $null
}

# Test 4: Get All Doctors (Public endpoint)
Write-Host "`n4. Testing Get All Doctors..." -ForegroundColor Yellow
try {
    $doctors = Invoke-WebRequest -Uri "http://localhost:8080/api/doctor/all" -UseBasicParsing
    Write-Host "✅ Get All Doctors: SUCCESS" -ForegroundColor Green
    Write-Host "Response: $($doctors.Content)" -ForegroundColor Gray
} catch {
    Write-Host "❌ Get All Doctors: FAILED" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 5: Analytics Summary (Admin endpoint)
Write-Host "`n5. Testing Analytics Summary..." -ForegroundColor Yellow
try {
    $analytics = Invoke-WebRequest -Uri "http://localhost:8080/api/admin/analytics/summary" -UseBasicParsing
    Write-Host "✅ Analytics Summary: SUCCESS" -ForegroundColor Green
    Write-Host "Response: $($analytics.Content)" -ForegroundColor Gray
} catch {
    Write-Host "❌ Analytics Summary: FAILED" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 6: H2 Console
Write-Host "`n6. Testing H2 Console..." -ForegroundColor Yellow
try {
    $h2 = Invoke-WebRequest -Uri "http://localhost:8080/h2-console" -UseBasicParsing
    Write-Host "✅ H2 Console: SUCCESS" -ForegroundColor Green
    Write-Host "H2 Console is accessible" -ForegroundColor Gray
} catch {
    Write-Host "❌ H2 Console: FAILED" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n=== Testing Complete ===" -ForegroundColor Green
