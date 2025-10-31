   // ---------- Background Images ----------
    const images = [
      '/ems_pics/EMS5.jpg',
      '/ems_pics/EMS6.jpg',
      '/ems_pics/EMS7.jpg',
      '/ems_pics/EMS8.jpg',
      '/ems_pics/EMS9.jpg',
      '/ems_pics/EMS10.jpg',
      '/ems_pics/EMS11.jpg',
      '/ems_pics/EMS12.jpg',
      '/ems_pics/EMS13.jpg'
    ];
    let imgIndex = 0;

    function changeBackground() {
      const hero = document.querySelector('.hero-section');
      hero.style.backgroundImage = `url(${images[imgIndex]})`;
      imgIndex = (imgIndex + 1) % images.length;
    }
    setInterval(changeBackground, 4000);
    changeBackground();

    // ---------- Quotes Section ----------
    const quotes = [
      // 🌸 Baby Showers
      "“Every new life is a reminder that hope never fades and love always finds a way to grow.”",
      "“A baby is a promise of joy, laughter, and endless beginnings.”",
      "“In every little heartbeat lies the rhythm of a family’s future.”",
      // 🎂 Birthdays
      "“Birthdays aren’t just about getting older—they’re about growing wiser, stronger, and more grateful.”",
      "“Each year is a chapter, and your story only gets richer with time.”",
      "“A birthday is life’s gentle reminder to celebrate where you’ve been and look forward to where you’re going.”",
      // 💍 Marriages
      "“A successful marriage is not about finding the right person—it’s about building the right partnership.”",
      "“Love doesn’t make the world go round, but it makes the journey worthwhile.”",
      "“Marriage is the art of growing together without losing yourself.”",
      // 🤝 Get-Togethers
      "“Moments may pass, but memories made together last a lifetime.”",
      "“The best gatherings are not about perfection—they’re about connection.”",
      "“When hearts reunite, time stands still and laughter becomes timeless.”",
      // 🕊️ Funerals
      "“Those we love never truly leave us; they live on in the quiet corners of our hearts.”",
      "“Every goodbye holds a story of love that was once beautifully shared.”",
      "“Life is a circle—endings are simply new beginnings in another form.”"
    ];

    let quoteIndex = 0;
    const quoteElement = document.getElementById("quoteText");

    function changeQuote() {
      quoteElement.classList.add("fade-out");
      setTimeout(() => {
        quoteElement.textContent = quotes[quoteIndex];
        quoteElement.classList.remove("fade-out");
        quoteElement.classList.add("fade-in");
      }, 500);
      quoteIndex = (quoteIndex + 1) % quotes.length;
    }

    setInterval(changeQuote, 4000);
    changeQuote();