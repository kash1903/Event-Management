🎉 Event Management System 🎯
---------------------------------------------------

🚀 A Full-Stack Web Application built with Spring Boot (Backend) and HTML, CSS, JavaScript (Frontend) to manage and attend events seamlessly.

🧠 Overview

Welcome to the Event Management System, a platform designed to bridge the gap between Admins 👨‍💼 and Clients 👥 in managing events efficiently.

✨ Admins can create, edit, and manage events.

🌟 Clients can explore, register, and attend events.

📊 Real-time Bar Chart Statistics display registrations and attendance for each event.

💻 Tech Stack

Layer	Technologies Used

🎨 Frontend	HTML, CSS, JavaScript

⚙️ Backend	Spring Boot

🗄️ Database	PostgreSQL

🌐 Deployment	Render.com

📊 Chart Visualization	Chart.js

🔐 Login Credentials

👨‍💼 Admin Account

Field	Value

✉️ Email	Admin@gmail.com
👤 Username	Admin
🔑 Password	1234

👥 Client Account

Field	Value
✉️ Email	Client@gmail.com
🔑 Password	1234


🧩 Modules & Features

👨‍💼 Admin Module

➕ Create new events

✏️ Edit or update existing events

❌ Delete events

📋 View all events created by the admin

📊 View event statistics in a bar graph (Registrations vs Attendance)

👥 Client Module

🔍 Browse or search for events by keyword

📝 Register for events

✅ Mark attendance and submit feedback

💬 View all available events posted by admins

🌐 Live API Endpoints

🔹 GET Mappings

Function	Endpoint

📜 All Events	/api/events/all

🔍 Search Event by Keyword	/api/events/search?keyword=zoho

🧑‍💼 Events by Admin	/api/events/byAdmin/1

📊 Event Stats	/api/event/4/stats


🔹 POST Mappings

Function	Endpoint

📝 Register for Event	/api/event/{currentEventId}/register?username={username}&email={email}

✅ Attend Event	/api/event/{currentEventId}/attend?username={username}&email={email}&feedback={feedback}

🎊 Create Event	/api/events/create

Sample JSON Body:

{
  "eventTitle": "Iron Fest",
  "date": "2025-11-29",
  "venue": "Goa IT Park",
  "speaker": "Dr. Aravind Krishna",
  "description": "Come and See the Goa Iron fest",
  "adminId": 101
}


🔹 PUT Mapping

Function	Endpoint

✏️ Edit Event	/api/events/update/{eventId}

🔹 DELETE Mapping

Function	Endpoint

❌ Delete Event	/api/events/delete/{eventId}


📈 Dashboard Preview

🧠 Admin View:

Displays total events created.

Graph showing Registrations vs Attendance for each event.

👥 Client View:

Event browsing & registration interface.

Option to mark attendance and give feedback after the event.


🧪 Testing

You can use Postman or any REST client to test API endpoints.
Or access the deployed version directly 🌍 on Render.



🧑‍💻 Author

Subramanian K A
📍 Java Full Stack Developer
💼 Passionate about building real-world applications that connect people and simplify workflows.