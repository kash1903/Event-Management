🎉 Event Management System – REST API Documentation

A complete backend API to manage events, user registration, attendance tracking, and admin analytics.
Built with Spring Boot + JPA, this system supports both Admin and Client roles.

🏗️ Base URL

http://localhost:8080/api



🔹 COMMON ENDPOINTS
🧾 Register User

POST /clients/register

Request Body (JSON)

{
  "username": "Mano",
  "userEmail": "kash270800@gmail.com",
  "password": "12345",
  "role": "ADMIN"
}

✅ Success Response
{
  "message": "User registered successfully!",
  "success": true,
  "user": {
    "id": 1,
    "username": "Mano",
    "userEmail": "kash270800@gmail.com",
    "role": "ADMIN"
  }
}

🔑 Login User

POST /clients/login?identifier={email/username}&password={password}

✅ Success Response
{
  "message": "Login successful!",
  "success": true,
  "user": {
    "id": 1,
    "username": "Mano",
    "userEmail": "kash270800@gmail.com",
    "role": "ADMIN"
  }
}

🧑‍💼 ADMIN ENDPOINTS
🆕 Create Event

POST /events/create

Request Body (JSON)
{
  "eventTitle": "AI Summit 2025",
  "description": "A tech event on artificial intelligence and innovation.",
  "location": "Chennai",
  "date": "2025-11-30"
}
✅ Response
{
  "message": "Event created successfully!",
  "success": true
}

✏️ Update Event

PUT /events/update/{eventId}

Example:
http://localhost:8080/api/events/update/3

Request Body (JSON)

{
  "eventTitle": "AI Summit 2025 - Updated",
  "location": "Bangalore"
}

✅ Response

{
  "message": "Event updated successfully!",
  "success": true
}

📋 Get All Events

GET /events/all

✅ Response

[
  {
    "id": 1,
    "eventTitle": "AI Summit 2025",
    "location": "Chennai",
    "date": "2025-11-30"
  }
]

❌ Delete Event

DELETE /events/delete/{eventId}

Example:
http://localhost:8080/api/events/delete/3

✅ Response

{
  "message": "Event deleted successfully!",
  "success": true
}

🔍 Search Event

GET /events/search?keyword={keyword}

Example:
http://localhost:8080/api/events/search?keyword=AI

✅ Response
[
  {
    "id": 1,
    "eventTitle": "AI Summit 2025",
    "location": "Chennai"
  }
]

📊 Event Statistics

GET /event/{eventId}/stats

Example:
http://localhost:8080/api/event/1/stats

✅ Response

{
  "eventTitle": "AI Summit 2025",
  "totalRegistered": 5,
  "totalAttended": 3,
  "success": true
}

👥 CLIENT ENDPOINTS
📝 Register for an Event

POST /event/{eventId}/register?username={username}&email={email}

Example:
http://localhost:8080/api/event/1/register?username=John&email=john@gmail.com

✅ Response

{
  "message": "Registered successfully for event!",
  "success": true,
  "eventTitle": "AI Summit 2025"
}

🎟️ Attend Event

POST /event/{eventId}/attend?username={username}&email={email}&feedback={feedback}

Example:

http://localhost:8080/api/event/1/attend?username=John&email=john@gmail.com&feedback=Great+session!

✅ Response

{
  "message": "Attendance recorded successfully!",
  "success": true,
  "feedback": "Great session!"
}

🔍 Search Event

GET /events/search?keyword={keyword}

Example:

http://localhost:8080/api/events/search?keyword=AI

✅ Response

[
  {
    "id": 1,
    "eventTitle": "AI Summit 2025",
    "location": "Chennai"
  }
]

🧠 Summary of System Flow

Admin registers, logs in, and creates events.

Clients can search, register, and attend events with feedback.

Admin Dashboard can view stats: total registrations and attendance.

APIs return clean, structured JSON ready for frontend integration.


