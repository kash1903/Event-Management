    // new code
    document.addEventListener("DOMContentLoaded", () => {
    const username = sessionStorage.getItem("username");
    const role = sessionStorage.getItem("role");
    const id = sessionStorage.getItem("clientId"); // if stored

    if (!username) {
        alert("Unauthorized access ❌");
        window.location.href = "/login";
        return;
    }

    document.getElementById("clientName").innerText = username;
    document.getElementById("clientRole").innerText = role;
});
 
    // new code
let currentEventId = null;
let currentAction = '';

window.onload = fetchAllEvents;

function fetchAllEvents() {
    // fetch('http://localhost:8080/api/events/all')
    fetch('https://event-management-2h31.onrender.com/api/events/all')
        .then(res => res.json())
        .then(data => renderEvents(data))
        .catch(err => console.error(err));
}

function renderEvents(events) {
    const container = document.getElementById('eventsContainer');
    container.innerHTML = '';

    events.forEach(event => {
        const card = document.createElement('div');
        card.className = 'event-card';
        card.innerHTML = `
            <h3>${event.eventTitle}</h3>
            <p><strong>Date:</strong> ${event.date}</p>
            <p><strong>Venue:</strong> ${event.venue}</p>
            <p><strong>Speaker:</strong> ${event.speaker}</p>
            <p>${event.description}</p>
            <div class="event-buttons">
                <button onclick="openModal(${event.id}, 'register')">Register</button>
                <button onclick="openModal(${event.id}, 'attend')">Attend</button>
            </div>
        `;
        container.appendChild(card);
    });
}

function searchEvents() {
    const keyword = document.getElementById('searchKeyword').value;
    // fetch(`http://localhost:8080/api/events/search?keyword=${keyword}`)
    fetch(`https://event-management-2h31.onrender.com/api/events/search?keyword=${keyword}`)
        .then(res => res.json())
        .then(data => renderEvents(data))
        .catch(err => console.error(err));
}

function openModal(eventId, action) {
    currentEventId = eventId;
    currentAction = action;
    document.getElementById('eventModal').style.display = 'flex';
    document.getElementById('modalTitle').innerText =
        action === 'register' ? 'Register for Event' : 'Attend Event';
    document.getElementById('feedback').style.display =
        action === 'attend' ? 'block' : 'none';
}

function closeModal() {
    document.getElementById('eventModal').style.display = 'none';
}

function submitAction() {
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const feedback = document.getElementById('feedback').value;

    if (!username || !email) {
        alert('Please fill all fields');
        return;
    }

    let url = '';
    if (currentAction === 'register') {
        // url = `http://localhost:8080/api/event/${currentEventId}/register?username=${username}&email=${email}`;
        url = `https://event-management-2h31.onrender.com/api/event/${currentEventId}/register?username=${username}&email=${email}`;
    } else {
        // url = `http://localhost:8080/api/event/${currentEventId}/attend?username=${username}&email=${email}&feedback=${encodeURIComponent(feedback)}`;
        url = `https://event-management-2h31.onrender.com/api/event/${currentEventId}/attend?username=${username}&email=${email}&feedback=${encodeURIComponent(feedback)}`;
    }

    fetch(url, { method: 'POST' })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            closeModal();
        })
        .catch(err => console.error(err));
}


function logout() {
    
window.location.href = "/"; // Redirect to landing page (index.html)
    
}