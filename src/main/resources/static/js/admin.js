
window.onload = function () {
  // ✅ Read from sessionStorage (not localStorage)
  const username = sessionStorage.getItem("username");
  const role = sessionStorage.getItem("role");
  const adminId = sessionStorage.getItem("adminId");

  // Redirect to login if not logged in
  if (!username || !role || !adminId) {
    alert("Please login first!");
    window.location.href = "login";
    return;
  }

  // ✅ Display info on header
  document.getElementById("usernameDisplay").innerText = username;
  document.getElementById("roleDisplay").innerText = role;

  // ✅ Store for later use
  window.adminId = adminId;

  fetchAdminEvents();
};

// ✅ Fetch events posted by this admin
function fetchAdminEvents() {
  fetch(`http://localhost:8080/api/events/byAdmin/${window.adminId}`)
    .then(res => res.json())
    .then(data => renderEvents(data))
    .catch(err => console.error("Error fetching events:", err));
}

// changed this 
// <button class="editBtn">Edit</button>
// <button class="deleteBtn">Delete</button>


// ✅ Render event cards
function renderEvents(events) {
  const container = document.getElementById("eventsContainer");
  container.innerHTML = '';

  if (!Array.isArray(events) || events.length === 0) {
    container.innerHTML = '<p style="text-align:center;color:gray;">No events found.</p>';
    return;
  }

  events.forEach(event => {
    const card = document.createElement("div");
    card.className = "event-card";
    card.innerHTML = `
      <h3>${event.eventTitle}</h3>
      <p><strong>Date:</strong> ${event.date}</p>
      <p><strong>Venue:</strong> ${event.venue}</p>
      <p><strong>Speaker:</strong> ${event.speaker}</p>
      <p>${event.description}</p>
      <div class="event-actions">
        
        <button class="editBtn" onclick='openEditModal(${JSON.stringify(event)})'>Edit</button>
        <button class="deleteBtn" onclick='deleteEvent(${event.id})'>Delete</button>
        
      </div>
    `;
    container.appendChild(card);
  });
}

// ✅ Search events
function searchEvents() {
  const keyword = document.getElementById("searchKeyword").value;
  if (!keyword.trim()) {
    fetchAdminEvents();
    return;
  }
  fetch(`http://localhost:8080/api/events/search?keyword=${keyword}`)
    .then(res => res.json())
    .then(data => renderEvents(data))
    .catch(err => console.error(err));
}

// ✅ Modal open/close
function openPostModal() {
  document.getElementById("postModal").style.display = "flex";
}
function closePostModal() {
  document.getElementById("postModal").style.display = "none";
}

// ✅ Create event (POST)
function submitEvent() {
  const event = {
    eventTitle: document.getElementById("eventTitle").value.trim(),
    date: document.getElementById("date").value,
    venue: document.getElementById("venue").value.trim(),
    speaker: document.getElementById("speaker").value.trim(),
    description: document.getElementById("description").value.trim(),
    adminId: window.adminId
  };

  if (!event.eventTitle || !event.date || !event.venue || !event.speaker) {
    alert("Please fill all fields!");
    return;
  }

  fetch("http://localhost:8080/api/events/create", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(event)
  })
    .then(res => res.json())
    .then(data => {
      alert("✅ Event created successfully!");
      closePostModal();
      fetchAdminEvents();
      loadEventDropdown(); // 🔁 refresh dropdown
    })
    .catch(err => console.error("Error creating event:", err));
}

// new code
let currentEditEventId = null;

// ✅ Open Edit Modal with pre-filled data
function openEditModal(event) {
  currentEditEventId = event.id;
  document.getElementById("editEventTitle").value = event.eventTitle;
  document.getElementById("editDate").value = event.date;
  document.getElementById("editVenue").value = event.venue;
  document.getElementById("editSpeaker").value = event.speaker;
  document.getElementById("editDescription").value = event.description;

  document.getElementById("editModal").style.display = "flex";
}

// ✅ Close Edit Modal
function closeEditModal() {
  document.getElementById("editModal").style.display = "none";
  currentEditEventId = null;
}

// ✅ Update Event (PUT)
function updateEvent() {
  if (!currentEditEventId) {
    alert("No event selected for editing!");
    return;
  }

  const updatedEvent = {
    eventTitle: document.getElementById("editEventTitle").value.trim(),
    date: document.getElementById("editDate").value,
    venue: document.getElementById("editVenue").value.trim(),
    speaker: document.getElementById("editSpeaker").value.trim(),
    description: document.getElementById("editDescription").value.trim(),
  };

  fetch(`http://localhost:8080/api/events/update/${currentEditEventId}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(updatedEvent)
  })
    .then(res => {
      if (!res.ok) throw new Error("Failed to update event");
      return res.json();
    })
    .then(data => {
      alert("✅ Event updated successfully!");
      closeEditModal();
      fetchAdminEvents();
    })
    .catch(err => console.error("Error updating event:", err));
}

// new code

// ✅ Delete event
function deleteEvent(eventId) {
  if (!confirm("⚠️ Are you sure you want to delete this event?")) return;

  fetch(`http://localhost:8080/api/events/delete/${eventId}`, {
    method: "DELETE"
  })
    .then(res => {
      if (!res.ok) throw new Error("Failed to delete event");
      return res.text(); // since your backend returns plain text (not JSON)
    })
    .then(msg => {
      alert(msg); // will show: Event deleted successfully ✅
      fetchAdminEvents(); // refresh the list
    })
    .catch(err => console.error("Error deleting event:", err));
}
// chart section start

const eventSelect = document.getElementById("eventSelect");
const adminId = 1; // 🔹 You can make this dynamic later if needed

// 🟢 Load all events of a specific admin into dropdown
async function loadEventDropdown() {
  try {
    const response = await fetch(`http://localhost:8080/api/events/byAdmin/${adminId}`);
    const events = await response.json();

    // Clear existing options (just in case)
    eventSelect.innerHTML = '<option value="">-- Choose Event --</option>';

    // Populate dropdown
    events.forEach(event => {
      const option = document.createElement("option");
      option.value = event.id;
      option.textContent = event.eventTitle;
      eventSelect.appendChild(option);
    });

    // mod
       // 🟢 Automatically show chart for the first event
    if (events.length > 0) {
      eventSelect.value = events[0].id;
      loadEventStats(events[0].id);
    }
    // mod
  } catch (error) {
    console.error("Error loading events:", error);
  }
}

// 🟢 Fetch stats & draw chart
async function loadEventStats(eventId) {
  try {
    const response = await fetch(`http://localhost:8080/api/event/${eventId}/stats`);
    const data = await response.json();

    if (!data.success) {
      console.error("Failed to fetch stats");
      return;
    }

    const ctx = document.getElementById('eventStatsChart').getContext('2d');

    // Destroy previous chart if exists
    if (window.eventChart) {
      window.eventChart.destroy();
    }

    // Create new chart
    window.eventChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Registered', 'Attended'],
        datasets: [{
          label: data.eventTitle,
          data: [data.totalRegistered, data.totalAttended],
          backgroundColor: ['#36A2EB', '#4BC0C0'],
          borderColor: ['#1E90FF', '#009688'],
          borderWidth: 1
        }]
      },
      options: {
        maintainAspectRatio: false,
        responsive: true,
        plugins: {
          legend: { display: true, position: 'bottom' },
          title: {
            display: true,
            text: `Stats for ${data.eventTitle}`,
            font: { size: 16 }
          }
        },
        scales: { y: { beginAtZero: true } }
      }
    });
  } catch (error) {
    console.error("Error loading stats:", error);
  }
}

// 🟢 When dropdown changes, update chart
eventSelect.addEventListener("change", () => {
  const eventId = eventSelect.value;
  if (eventId) {
    loadEventStats(eventId);
  }
});

// Load dropdown on page load
loadEventDropdown();

// try 3 end 

// chart section end 


// ✅ Logout
function logout() {
  sessionStorage.clear();
  window.location.href = "/";
}


